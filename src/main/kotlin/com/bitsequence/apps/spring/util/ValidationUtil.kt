package com.bitsequence.apps.spring.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.validation.Errors

object ValidationUtil {

	private val log: Logger = LoggerFactory.getLogger(ValidationUtil::class.java)

	fun getAndLogError(errors: Errors): String {
		log.debug("getAndLogErrors()")

		if (log.isErrorEnabled) {
			errors.fieldErrors
					.forEach {
						log.error(
								"Finding a field that is not in accordance with the requirement. (field={}, message={})",
								it.field,
								it.defaultMessage)
					}
		}

		val problemField: String = errors.fieldError?.field ?: "Unknown field"
		val message: String = errors.fieldError?.defaultMessage ?: "Unknown message"

		return "Validation for '$problemField' failed because of '$message'."
	}

}