package com.springsecurity.demo.security;

import com.springsecurity.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MyUserDetailsService userDetailsService;

    /*Authentication method*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("Admin").and().withUser("user").password("user").roles("User");
        auth.userDetailsService(userDetailsService);
    }

    // Authorization - Should be from most secure to least one
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // To allow access to any url without permission is by using permitAll() method
        System.out.println("Accessign URL : ");
        http.authorizeRequests().
                antMatchers("/admin").hasRole("ADMIN").
                antMatchers("/user").hasAnyRole("USER", "ADMIN").
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
