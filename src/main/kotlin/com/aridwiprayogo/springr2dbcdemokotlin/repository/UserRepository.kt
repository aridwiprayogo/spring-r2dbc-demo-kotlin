package com.aridwiprayogo.springr2dbcdemokotlin.repository

import com.aridwiprayogo.springr2dbcdemokotlin.domain.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface UserRepository : CoroutineCrudRepository<User,UUID>{
    suspend fun findUserByNameOrPassword(name: String, password: String): User?
}
