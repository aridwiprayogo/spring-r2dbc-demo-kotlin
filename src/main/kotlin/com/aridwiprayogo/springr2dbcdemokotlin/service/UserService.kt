package com.aridwiprayogo.springr2dbcdemokotlin.service

import com.aridwiprayogo.springr2dbcdemokotlin.domain.Users
import com.aridwiprayogo.springr2dbcdemokotlin.payload.request.UserDto
import java.util.*

interface UserService {
    suspend fun saveUser(userDto: UserDto): Users
    suspend fun getAllUser(): List<Users>
    suspend fun getUserByNameOrPassword(name: String, password: String): Users
    suspend fun updateUser(id:UUID, userDto: UserDto): Users
    suspend fun delete(id: UUID): String
}