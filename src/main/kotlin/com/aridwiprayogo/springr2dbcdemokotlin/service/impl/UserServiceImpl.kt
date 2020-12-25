package com.aridwiprayogo.springr2dbcdemokotlin.service.impl

import com.aridwiprayogo.springr2dbcdemokotlin.domain.User
import com.aridwiprayogo.springr2dbcdemokotlin.repository.UserRepository
import com.aridwiprayogo.springr2dbcdemokotlin.service.UserService
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override suspend fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    override suspend fun getAllUser(): List<User> {
        return userRepository.findAll().toList()
    }

    override suspend fun getUserByNameOrPassword(name: String, password: String): User {
        return userRepository.findUserByNameOrPassword(name =name, password =password)
            ?: throw RuntimeException()
    }

    override suspend fun updateUser(id:UUID, user: User): User =
        userRepository.findById(id = id)?.let { user1 ->
            user1.copy(
                name = user.name,
                password = user.password,
                description = user.description
            )
        userRepository.save(user1)
    } ?: throw RuntimeException()

    override suspend fun delete(user: User): String {
        userRepository.delete(user)
        return "user ${user.id} has been removed"
    }
}
