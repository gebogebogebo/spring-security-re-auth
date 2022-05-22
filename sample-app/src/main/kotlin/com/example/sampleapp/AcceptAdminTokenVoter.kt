package com.example.sampleapp

import com.example.sampleapp.util.AppUtil
import org.springframework.security.access.AccessDecisionVoter
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.core.Authentication

class AcceptAdminTokenVoter : AccessDecisionVoter<Any?> {
    override fun supports(attribute: ConfigAttribute?): Boolean {
        // ROLE_ADMIN only
        return attribute?.toString()?.contains(AppUtil.Role.ROLE_ADMIN.name) ?: false
    }

    override fun supports(clazz: Class<*>?): Boolean {
        return true
    }

    override fun vote(
        authentication: Authentication?,
        `object`: Any?,
        attributes: MutableCollection<ConfigAttribute>?
    ): Int {
        val supports = attributes?.find { supports(it) }
        if( supports != null ) {
            // check expired
            val token = authentication?.authorities?.find { it is ExpireGrantedAuthority }
            if (token != null && token is ExpireGrantedAuthority) {
                if (token.expired()) {
                    return AccessDecisionVoter.ACCESS_DENIED
                }
            }
        }
        return AccessDecisionVoter.ACCESS_GRANTED
    }

}
