package com.aridwiprayogo.springr2dbcdemokotlin.service.impl

import com.aridwiprayogo.springr2dbcdemokotlin.domain.Users
import com.aridwiprayogo.springr2dbcdemokotlin.payload.request.UserDto
import com.aridwiprayogo.springr2dbcdemokotlin.repository.UserRepository
import com.aridwiprayogo.springr2dbcdemokotlin.service.UserService
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override suspend fun saveUser(userDto: UserDto): Users {
        val users = Users(
            name = userDto.name,
            password = userDto.password,
            description = userDto.description
        )
        return userRepository.save(users).awaitFirst()
            ?: throw RuntimeException()
    }

    override suspend fun getAllUser(): List<Users> {
        return userRepository.findAll().doOnError {
            throw RuntimeException("", it)
        }.asFlow().toList()
    }

    override suspend fun getUserByNameOrPassword(name: String, password: String): Users {
        return userRepository.findByNameOrPassword(name = name, password = password)
            .awaitFirst() ?: throw RuntimeException()
    }

    override suspend fun updateUser(id:UUID, userDto: UserDto): Users =
        userRepository.findById(id).map { user ->
            user.copy(
                name = userDto.name,
                password = userDto.password,
                description = userDto.description
            ).let{
                userRepository.save(it).block() ?: throw RuntimeException()
            }
    }.awaitFirstOrNull() ?: throw RuntimeException()

    override suspend fun delete(id: UUID): String {
        userRepository.deleteById(id)
        return "user $id has been removed"
    }
}
