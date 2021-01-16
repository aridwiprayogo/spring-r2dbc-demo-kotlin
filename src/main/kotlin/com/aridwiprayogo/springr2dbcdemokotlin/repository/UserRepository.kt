package com.aridwiprayogo.springr2dbcdemokotlin.repository

import com.aridwiprayogo.springr2dbcdemokotlin.domain.Users
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.*

@Repository
interface UserRepository : ReactiveSortingRepository<Users,UUID>{
    fun findByNameOrPassword(name: String, password: String): Mono<Users>
}
