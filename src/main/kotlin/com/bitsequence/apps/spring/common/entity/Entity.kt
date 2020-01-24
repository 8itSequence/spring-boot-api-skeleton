package com.bitsequence.apps.spring.common.entity

import java.io.Serializable

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Entity(

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		open val id: Long

) : Serializable