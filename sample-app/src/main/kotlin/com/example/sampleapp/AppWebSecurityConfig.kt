package com.example.sampleapp

import com.example.sampleapp.util.AppUtil
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

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
            .antMatchers("/settings").hasAuthority(AppUtil.Role.ROLE_ADMIN.name)
            .anyRequest().hasAuthority(AppUtil.Role.ROLE_USER.name)

        // Login Page
        http
            .formLogin()
            .loginPage("/login").permitAll()

        // for h2db debug
        http.csrf().disable()
        http.headers().frameOptions().disable()

        // for Re-Authentication Redirect
        http
            .exceptionHandling()
            .accessDeniedHandler(CustomAccessDeniedHandler())

        // for Re-Authentication Security Filter
        val filter = CustomUsernamePasswordAuthenticationFilter()
        filter.setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/login", "POST"))
        filter.setAuthenticationManager(authenticationManagerBean())
        filter.setAuthenticationFailureHandler(SimpleUrlAuthenticationFailureHandler("/login?error"))
        http.addFilterAt(filter, CustomUsernamePasswordAuthenticationFilter::class.java)
    }

}
