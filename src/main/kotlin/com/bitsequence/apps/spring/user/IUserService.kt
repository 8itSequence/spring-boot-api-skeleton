package com.bitsequence.apps.spring.user

import com.bitsequence.apps.spring.common.entity.IEntityService
import com.bitsequence.apps.spring.user.db.User

import java.util.Optional

interface IUserService : IEntityService<User> {

	fun findOne(
			userId: Long = 0,
			userEmail: String = ""
	) : Optional<User>

	fun findAll(): MutableList<User>

	fun update(user: User, model: User): User
}