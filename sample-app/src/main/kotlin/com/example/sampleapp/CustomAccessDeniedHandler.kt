package com.example.sampleapp

import com.example.sampleapp.util.AppUtil
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler: AccessDeniedHandler {
    private val requestCache: RequestCache = HttpSessionRequestCache()

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        val user = AppUtil.getLoginUser()
        if( user != null ){
            // ここで本来の遷移先をsaveしておくと、認証成功後に↓でリダイレクトされる
            // - SavedRequestAwareAuthenticationSuccessHandler.onAuthenticationSuccess()
            requestCache.saveRequest(request, response)

            // /loginにリダイレクトする
            val redirectStrategy = DefaultRedirectStrategy()
            redirectStrategy.sendRedirect(request, response, "/login")
        }
    }
}
