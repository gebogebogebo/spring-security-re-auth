package com.example.sampleapp.controller

import com.example.sampleapp.util.AppUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletRequest

@Controller
class MyPageController {
    @GetMapping("mypage")
    fun mypage(
        request: HttpServletRequest,
        model: Model,
    ): String {
        val user = AppUtil.getLoginUser()
        model.addAttribute("userName", user?.username)

        return "mypage"
    }
}
