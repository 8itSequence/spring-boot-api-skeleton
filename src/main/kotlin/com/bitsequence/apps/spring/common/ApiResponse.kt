package com.bitsequence.apps.spring.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

sealed class ApiResponse {

	data class Success<out D>(
			val success: String = "true",
			val data: D
	) : ApiResponse()

	data class Error(
			val success: String = "false",
			private val errorMessage: String = "",
			private val errorDetailsMessage: String = "",
			private val errorException: Exception? = null
	) : ApiResponse() {

		private val detailError = DetailError(
				errorMessage,
				errorDetailsMessage,
				errorException
		)

		val cause: String = detailError.message
	}

	protected data class DetailError(
			private val errorMessage: String = "",
			private val errorDetailsMessage: String = "",
			private val exception: Exception? = null
	) {
		companion object {
			private val log: Logger = LoggerFactory.getLogger(DetailError::class.java)
		}

		private fun getDetailsMessage(): String {
			if (exception != null) {
				if (log.isErrorEnabled) {
					log.error("An error occurred.", exception)
				}

				return "$errorMessage $errorDetailsMessage Cause: " + exception.localizedMessage
			}

			return "$errorMessage $errorDetailsMessage"
		}

		val message: String
			get() = getDetailsMessage()
	}
}