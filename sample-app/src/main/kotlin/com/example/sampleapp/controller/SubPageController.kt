package com.example.sampleapp.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SubPageController {
    @GetMapping("/subpage")
    fun settings(): String {
        return "subpage.html"
    }
}
