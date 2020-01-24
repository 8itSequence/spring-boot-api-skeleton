package com.bitsequence.apps.spring.internal

import com.bitsequence.apps.spring.user.IUserService
import com.bitsequence.apps.spring.user.db.User

import com.github.javafaker.Faker

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

import java.util.Locale

@Component
internal class DbPopulation(
		private val userService: IUserService
) : CommandLineRunner {

	private companion object {
		val log: Logger = LoggerFactory.getLogger(DbPopulation::class.java)
	}

	@Value(value = "\${app.db.populate.allowed}")
	private var isEnabled = false

	private lateinit var faker: Faker

	override fun run(vararg args: String?) {
		log.debug("Executing db population ...")

		if (isEnabled) {
			faker = Faker.instance(Locale.GERMAN)

			for (i: Int in 1..100) {
				val user = User(
					email = faker.bothify("????????##@gmail.com"),
					firstName = faker.name().firstName(),
					lastName = faker.name().lastName(),
					phone = faker.phoneNumber().phoneNumber()
				)

				log.debug("User created: {}", user)
				userService.save(user)
			}

			log.debug("... population finished.")
		} else {
			log.debug("Population is disabled.")
		}
	}

}