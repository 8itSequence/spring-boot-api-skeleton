package com.bitsequence.apps.spring.common.entity

import java.util.Optional

interface IEntityService<T : BaseEntity> {

	fun findEntity(id: Long): Optional<T>

	fun save(entity: T): T

	fun delete(id: Long): Set<String>
	fun delete(entity: T): Set<String>

}