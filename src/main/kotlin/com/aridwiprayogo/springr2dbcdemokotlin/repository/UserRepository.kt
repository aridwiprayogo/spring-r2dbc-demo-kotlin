package com.aridwiprayogo.springr2dbcdemokotlin.repository

import com.aridwiprayogo.springr2dbcdemokotlin.domain.Users
import org.springframework.data.repository.kotlin.CoroutineSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : CoroutineSortingRepository<Users,UUID>{
    fun findByNameOrPassword(name: String, password: String): Users?
}
