package com.example.sampleapp

import com.example.sampleapp.util.AppUtil
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomUsernamePasswordAuthenticationFilter: UsernamePasswordAuthenticationFilter() {
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        return super.attemptAuthentication(request, response)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        val loginUser = AppUtil.getLoginUser()
        if(loginUser != null){
            // 再認証のときは単に failureHandler を実行するだけにする
            failureHandler.onAuthenticationFailure(request, response, failed)
        } else {
            // 初回認証のときは通常の処理
            super.unsuccessfulAuthentication(request, response, failed)
        }
    }

}
