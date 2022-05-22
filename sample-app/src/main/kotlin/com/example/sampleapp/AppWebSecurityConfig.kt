package com.example.sampleapp

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class AppWebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/css/**","/js/**","/images/**");
    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/login").permitAll()
            .anyRequest().authenticated()

        // Login Page
        http
            .formLogin()
            .loginPage("/login").permitAll()

        // for h2db debug
        http.csrf().disable()
        http.headers().frameOptions().disable()

    }

}
