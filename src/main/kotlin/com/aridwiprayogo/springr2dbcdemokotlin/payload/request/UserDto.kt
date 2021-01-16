package com.aridwiprayogo.springr2dbcdemokotlin.payload.request

data class UserDto(
    val name: String = "",
    val password: String = "",
    val description: String = "",
)
