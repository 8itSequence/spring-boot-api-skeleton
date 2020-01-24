package com.bitsequence.apps.spring.user.db

import com.bitsequence.apps.spring.common.entity.BaseEntity
import com.fasterxml.jackson.annotation.JsonProperty

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "app_user")
data class User(
		override val id: Long = 0,
		override val createdBy: Long = 0,
		override val updatedBy: Long = 0,

		@Column(name = "email")
		@JsonProperty(
				value = "email",
				required = true)
		@get:Size(min = 4, max = 64, message = "Email must contain at least 4 and max 64 characters.")
		@get:NotBlank(message = "Email must not be empty.")
		val email: String = "",

		@Column(name = "first_name")
		val firstName: String = "",

		@Column(name = "last_name")
		val lastName: String = "",

		@Column(name = "phone")
		val phone: String = ""

) : BaseEntity(
		id = id,
		createdBy = createdBy,
		updatedBy = updatedBy
) {

	fun copy(user: User) = User(
			id = this.id,
			email = this.email,
			firstName = user.firstName,
			lastName = user.lastName,
			phone = user.phone,
			createdBy = this.createdBy,
			updatedBy = this.id
	)

}