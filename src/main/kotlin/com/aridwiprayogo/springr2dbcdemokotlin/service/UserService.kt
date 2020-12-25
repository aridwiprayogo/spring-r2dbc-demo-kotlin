package com.aridwiprayogo.springr2dbcdemokotlin.service

import com.aridwiprayogo.springr2dbcdemokotlin.domain.User
import java.util.*

interface UserService {
    suspend fun saveUser(user: User): User
    suspend fun getAllUser(): List<User>
    suspend fun getUserByNameOrPassword(name: String, password: String): User
    suspend fun updateUser(id:UUID, user: User): User
    suspend fun delete(user: User): String
}