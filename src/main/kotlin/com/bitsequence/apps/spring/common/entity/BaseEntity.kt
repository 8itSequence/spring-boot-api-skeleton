package com.bitsequence.apps.spring.common.entity

import com.fasterxml.jackson.annotation.JsonIgnore

import java.io.Serializable

import javax.persistence.Column
import javax.persistence.MappedSuperclass

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy

@MappedSuperclass
abstract class BaseEntity(

		override val id: Long,

		@get:JsonIgnore
		@CreatedBy
		@Column(
				name = "created_by",
				nullable = false)
		open val createdBy: Long,

		@get:JsonIgnore
		@LastModifiedBy
		@Column(name = "updated_by")
		open val updatedBy: Long = id

) : AuditableEntity(id = id), Serializable