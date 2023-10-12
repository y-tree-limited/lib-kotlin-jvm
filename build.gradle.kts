import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `java-library`
  `maven-publish`

  id("org.springframework.boot") version "3.2.0-M3"
  id("io.spring.dependency-management") version "1.1.3"
  id("com.ytree.gradle.maven-publish") version "1.0.0"
  id("org.jlleitschuh.gradle.ktlint") version "11.3.1"

  kotlin("jvm") version "1.9.10"
  kotlin("plugin.spring") version "1.9.10"
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")
ktlint {
  verbose.set(true)
  outputToConsole.set(true)
  coloredOutput.set(true)
  reporters {
    reporter(ReporterType.CHECKSTYLE)
  }
}

group = "com.ytree"
version = "0.0.1-SNAPSHOT"
extra["springCloudVersion"] = "2022.0.4"

java {
  withJavadocJar()
  withSourcesJar()
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

repositories {
  maven("https://ytree.jfrog.io/artifactory/maven-group") {
    credentials(HttpHeaderCredentials::class) {
      val jFrogArtifactoryReferenceToken: String by project
      name = "Authorization"
      value = "Bearer $jFrogArtifactoryReferenceToken"
      authentication.create<HttpHeaderAuthentication>("header")
    }
  }
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  
  implementation("com.ytree:lib-common:2.0.0")
  
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("com.ytree:lib-testcommon:1.1.0")
  testImplementation("io.mockk:mockk:1.13.4")
}

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
  enabled = true
}

tasks.getByName<Jar>("bootJar") {
  enabled = false
}