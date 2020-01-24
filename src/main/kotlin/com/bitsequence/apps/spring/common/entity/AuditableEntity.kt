package com.bitsequence.apps.spring.common.entity

import com.fasterxml.jackson.annotation.JsonIgnore

import java.io.Serializable
import java.time.Instant

import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class AuditableEntity(

		override val id: Long,

		@CreatedDate
		@Column(
				name = "created_at",
				nullable = false,
				updatable = false,
				columnDefinition = "DATETIME")
		@get:JsonIgnore
		open val createdAt: Instant = Instant.now(),

		@LastModifiedDate
		@Column(
				name = "updated_at",
				nullable = false,
				updatable = true,
				columnDefinition = "DATETIME")
		@get:JsonIgnore
		open val updatedAt: Instant = Instant.now()

) : Entity(id = id), Serializable