package com.example.sampleapp.controller

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.WebAttributes
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpSession

@Controller
class LoginController {
    // - 認証失敗時に error パラメータが spring によって付与される
    @GetMapping("login")
    fun login(
        @RequestParam(value = "error", required = false) error: String?,
        @RequestParam(value = "logout", required = false) logout: String?,
        model: Model,
        session: HttpSession,
    ): String {
        model.addAttribute("showErrorMsg", false)
        model.addAttribute("showLogoutedMsg", false)

        if (error != null) {
            val ex = session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) as AuthenticationException?
            if (ex != null) {
                model.addAttribute("showErrorMsg", true)
                model.addAttribute("errorMsg", ex.message)
            }
        } else if (logout != null) {
            model.addAttribute("showLogoutedMsg", true)
            model.addAttribute("logoutedMsg", "Logouted")
        }

        return "login"
    }
}
