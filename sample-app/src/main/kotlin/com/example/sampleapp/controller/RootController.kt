package com.example.sampleapp.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RootController {
    @GetMapping("/")
    fun root(): String {
        return "redirect:mypage"
    }
}
