package com.aridwiprayogo.springr2dbcdemokotlin.handler

import com.aridwiprayogo.springr2dbcdemokotlin.domain.User
import com.aridwiprayogo.springr2dbcdemokotlin.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI
import java.util.*

@Component
class UserHandler (private val userService: UserService) {

    suspend fun saveUser(serverRequest: ServerRequest): ServerResponse{
        val user = serverRequest.awaitBody<User>()
        val saveUser = userService.saveUser(user)
        return ServerResponse.created(URI.create("")).bodyValueAndAwait(saveUser)
    }

    suspend fun getAllUser(serverRequest: ServerRequest): ServerResponse {
        val users = userService.getAllUser()
        return ServerResponse.ok().bodyValueAndAwait(users)
    }

    suspend fun getUserByName(serverRequest: ServerRequest): ServerResponse {
        val username: String = serverRequest.awaitFormData()["username"]?.get(0)?:""
        val password: String = serverRequest.awaitFormData()["password"]?.get(0)?:""
        val users = userService.getUserByNameOrPassword(username,password)
        return ServerResponse.ok().bodyValueAndAwait(users)
    }

    suspend fun updateUser(serverRequest: ServerRequest): ServerResponse {
        val id: String = serverRequest.pathVariable("id")
        val uuid = UUID.fromString(id)
        val user = serverRequest.awaitBody<User>()
        userService.updateUser(uuid,user)
        return ServerResponse.ok().bodyValueAndAwait("")
    }

    suspend fun deleteUser(serverRequest: ServerRequest): ServerResponse {
        val user = serverRequest.awaitBody<User>()
        userService.delete(user = user)
        return ServerResponse.noContent().buildAndAwait()
    }
}
