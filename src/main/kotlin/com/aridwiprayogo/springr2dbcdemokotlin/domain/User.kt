package com.aridwiprayogo.springr2dbcdemokotlin.domain

import org.springframework.data.annotation.Id
import java.util.*
import org.springframework.data.relational.core.mapping.Table

@Table
data class User(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val password: String = "",
    val description: String = "",
)
