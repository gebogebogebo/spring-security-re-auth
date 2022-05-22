package com.example.sampleapp

import org.springframework.security.core.GrantedAuthority
import java.util.Date

class ExpireGrantedAuthority(
    private val role: String,
    private val expire: Date,
): GrantedAuthority {

    fun expired(): Boolean {
        return expire.before(Date())
    }

    override fun getAuthority(): String {
        return role
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        return if (other is ExpireGrantedAuthority) {
            role == other.role
        } else false
    }

    override fun hashCode(): Int {
        return role.hashCode()
    }

    override fun toString(): String {
        return this.role
    }
}
