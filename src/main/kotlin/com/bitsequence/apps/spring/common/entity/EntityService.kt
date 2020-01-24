package com.bitsequence.apps.spring.common.entity

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.Optional

abstract class EntityService<T : BaseEntity>(
		protected open val repository: IEntityRepository<T>
) : IEntityService<T> {

	companion object {
		val log: Logger = LoggerFactory.getLogger(EntityService::class.java)
	}

	override fun findEntity(id: Long): Optional<T> {
		log.debug("Find the entity by the given id.")
		return repository.findById(id)
	}

	override fun save(entity: T): T {
		val dbEntity = repository.save(entity)
		log.debug("New entity was successfully created.")
		return dbEntity
	}

	override fun delete(id: Long): Set<String> {
		val dbEntity = repository.findById(id)
		if (dbEntity.isEmpty) {
			return setOf("There is no such entity to delete.")
		}

		return delete(dbEntity.get())
	}

	override fun delete(entity: T): Set<String> {
		repository.deleteById(entity.id)
		log.debug("The entity deleted successfully.")
		return emptySet()
	}

}