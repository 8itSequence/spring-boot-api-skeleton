package com.bitsequence.apps.spring.user.api

import com.bitsequence.apps.spring.common.ApiResponse
import com.bitsequence.apps.spring.user.IUserService
import com.bitsequence.apps.spring.user.db.User
import com.bitsequence.apps.spring.util.ValidationUtil

import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UserController(
		private val userService: IUserService
) {

	@GetMapping(value = ["/{id}"])
	fun getUser(
			@PathVariable(name = "id")
			userId: Long
	): ResponseEntity<ApiResponse> {
		val user = userService.findOne(userId = userId)
		if (user.isPresent) {
			return ResponseEntity
					.ok(ApiResponse.Success(data = user.get()))
		}

		return ResponseEntity
				.badRequest()
				.body(ApiResponse.Error(
						errorMessage = "No such user exists $userId")
				)
	}

	@GetMapping
	fun getUsers(): ResponseEntity<ApiResponse> = ResponseEntity
			.ok()
			.body(ApiResponse.Success(data = userService.findAll()))

	@PostMapping
	fun createUser(
			@Valid
			@RequestBody
			user: User,
			errors: Errors
	): ResponseEntity<ApiResponse> {
		if (errors.hasErrors()) {
			return ResponseEntity
					.badRequest()
					.body(ApiResponse.Error(
							errorMessage = "Something went wrong with the client request.",
							errorDetailsMessage = ValidationUtil.getAndLogError(errors)
					))
		}

		val existingUser = userService.findOne(userEmail = user.email)
		if (existingUser.isPresent) {
			return ResponseEntity
					.badRequest()
					.body(ApiResponse.Error(
							errorMessage = "Something went wrong with the client request.",
							errorDetailsMessage = "E-mail address is already assigned, email=${user.email}.")
					)
		}

		val newUser = userService.save(User(email = user.email))
		return ResponseEntity
				.ok()
				.body(ApiResponse.Success(data = newUser))
	}

	@PutMapping(value = ["/{id}"])
	fun updateUser(
			@PathVariable(name = "id")
			userId: Long,
			@RequestBody
			model: User
	): ResponseEntity<ApiResponse> {
		val dbUser = userService.findOne(userId)
		if (dbUser.isEmpty) {
			return ResponseEntity
					.badRequest()
					.body(ApiResponse.Error(
							errorMessage = "Something went wrong with the client request.",
							errorDetailsMessage = "No such user exists.")
					)
		}

		val user: User = userService.update(dbUser.get(), model)

		return ResponseEntity
				.ok()
				.body(ApiResponse.Success(data = user))
	}

	@DeleteMapping(value = ["/{id}"])
	fun deleteUser(
			@PathVariable(name = "id")
			userId: Long): ResponseEntity<ApiResponse> {
		val errors = userService.delete(userId)
		if (errors.isNotEmpty()) {
			return ResponseEntity
					.badRequest()
					.body(ApiResponse.Error(
							errorMessage = "Something went wrong with the client request.",
							errorDetailsMessage = errors.first())
					)
		}

		return ResponseEntity
				.ok()
				.body(ApiResponse.Success(data = "User deleted successfully."))
	}

}