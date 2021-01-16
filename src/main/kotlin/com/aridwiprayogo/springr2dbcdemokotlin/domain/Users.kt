package com.aridwiprayogo.springr2dbcdemokotlin.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(value = "users")
data class Users(
    @Id @JvmField var id: UUID? = null,
    val name: String = "",
    val password: String = "",
    val description: String = "",
) : Persistable<UUID> {

    @JsonIgnore
    override fun getId(): UUID? {
        return this.id
    }

    @JsonIgnore
    @Transient
    private fun b1(): Boolean {
        return Objects.isNull(id)
    }

    @JsonIgnore
    @Transient
    override fun isNew(): Boolean {
        return b1()
    }
}
