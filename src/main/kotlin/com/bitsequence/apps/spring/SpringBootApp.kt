package com.bitsequence.apps.spring

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@EnableAutoConfiguration
@SpringBootApplication
class SpringBootApp

fun main(args: Array<String>) {
	runApplication<SpringBootApp>(*args)
}

@RestController
@RequestMapping(value = ["/api/v1/greeting"])
class GreetingController {

	@GetMapping
	fun greeting(
			@RequestParam(value = "name")
			name: String
	) = mapOf(
			"status" to "success",
			"message" to "Hello from Spring, $name"
	)

}