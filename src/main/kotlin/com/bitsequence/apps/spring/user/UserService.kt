package com.bitsequence.apps.spring.user

import com.bitsequence.apps.spring.common.entity.EntityService
import com.bitsequence.apps.spring.user.db.IUserRepository
import com.bitsequence.apps.spring.user.db.User

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
		private val userRepository: IUserRepository
) : EntityService<User>(userRepository), IUserService {

	companion object {
		val log: Logger = LoggerFactory.getLogger(UserService::class.java)
	}

	override fun findOne(
			userId: Long,
			userEmail: String): Optional<User> {
		log.debug("findOne()")
		if (userEmail.isNotBlank()) {
			log.debug("Trying to find the user by the user email.")
			return userRepository.findByEmail(userEmail)
		}

		log.debug("Trying to find the user by the user id.")
		return userRepository.findById(userId)
	}

	override fun findAll(): MutableList<User> {
		log.debug("findAll()")
		return userRepository.findAll()
	}

	override fun update(user: User, model: User): User {
		log.debug("update()")

		val userToStore = user.copy(model)
		return save(userToStore)
	}

}