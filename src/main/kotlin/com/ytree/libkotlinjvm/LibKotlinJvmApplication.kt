package com.ytree.libkotlinjvm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibKotlinJvmApplication

fun main(args: Array<String>) {
	runApplication<LibKotlinJvmApplication>(*args)
}
