package com.bitsequence.apps.spring

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableAutoConfiguration
@SpringBootApplication
class SpringBootApp

fun main(args: Array<String>) {
	runApplication<SpringBootApp>(*args)
}
