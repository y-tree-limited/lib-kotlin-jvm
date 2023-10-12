rootProject.name = "lib-kotlin-jvm"

pluginManagement {
  repositories {
    maven {
      url = uri("https://ytree.jfrog.io/artifactory/maven-group")
      credentials(HttpHeaderCredentials::class) {
        val jFrogArtifactoryReferenceToken: String by settings
        name = "Authorization"
        value = "Bearer $jFrogArtifactoryReferenceToken"
        authentication.create<HttpHeaderAuthentication>("header")
      }
    }
    gradlePluginPortal()
  }
}

