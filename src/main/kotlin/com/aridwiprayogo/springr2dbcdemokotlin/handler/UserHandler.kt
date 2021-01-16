package com.aridwiprayogo.springr2dbcdemokotlin.handler

import com.aridwiprayogo.springr2dbcdemokotlin.domain.Users
import com.aridwiprayogo.springr2dbcdemokotlin.payload.request.UserDto
import com.aridwiprayogo.springr2dbcdemokotlin.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI
import java.util.*

@Component
class UserHandler (private val userService: UserService) {

    suspend fun saveUser(serverRequest: ServerRequest): ServerResponse{
        val userDto = serverRequest.awaitBody<UserDto>()
        val saveUser: Users = userService.saveUser(userDto)
        return ServerResponse.created(URI.create("")).bodyValueAndAwait(saveUser)
    }

    suspend fun getAllUser(serverRequest: ServerRequest): ServerResponse {
        val users = userService.getAllUser()
        return ServerResponse.ok().bodyValueAndAwait(users)
    }

    suspend fun getUserByNameOrPassword(serverRequest: ServerRequest): ServerResponse {
        val username: String = serverRequest.awaitFormData()["username"]?.get(0)?:""
        val password: String = serverRequest.awaitFormData()["password"]?.get(0)?:""
        val users = userService.getUserByNameOrPassword(username,password)
        return ServerResponse.ok().bodyValueAndAwait(users)
    }

    suspend fun updateUser(serverRequest: ServerRequest): ServerResponse {
        val id: String = serverRequest.pathVariable("id")
        val uuid = UUID.fromString(id)
        val userDto = serverRequest.awaitBody<UserDto>()
        userService.updateUser(uuid,userDto)
        return ServerResponse.ok().bodyValueAndAwait("")
    }

    suspend fun deleteUser(serverRequest: ServerRequest): ServerResponse {
        val id: String = serverRequest.pathVariable("id")
        userService.delete(id= UUID.fromString(id))
        return ServerResponse.noContent().buildAndAwait()
    }
}
