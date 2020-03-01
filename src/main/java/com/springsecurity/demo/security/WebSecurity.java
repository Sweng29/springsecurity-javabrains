package com.springsecurity.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
    Spring boot Authentication & Authorization - basics* */

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


    /*Authentication method*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("Admin").and().withUser("user").password("user").roles("User");
    }

    // Authorization - Should be from most secure to least one
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // To allow access to any url without permission is by using permitAll() method
        http.authorizeRequests().
                antMatchers("/admin").
                hasRole("Admin").
                antMatchers("/user").
                hasAnyRole("User", "Admin").
                antMatchers("/", "static/css", "static/js").
                permitAll().
                and().
                formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
