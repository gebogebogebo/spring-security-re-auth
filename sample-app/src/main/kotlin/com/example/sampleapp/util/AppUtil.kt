package com.example.sampleapp.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User

class AppUtil {
    companion object {
        fun getLoginUser(): User? {
            val context = SecurityContextHolder.getContext()
            if(context.authentication == null){
                return null
            }
            val principal = context.authentication.principal
            return if (principal is User) {
                principal
            } else {
                null
            }
        }
    }
}
