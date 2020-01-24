package com.bitsequence.apps.spring.user.db

import com.bitsequence.apps.spring.common.entity.IEntityRepository

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import java.util.Optional

@Repository
@Transactional
interface IUserRepository : IEntityRepository<User> {

	fun findByEmail(email: String): Optional<User>

}