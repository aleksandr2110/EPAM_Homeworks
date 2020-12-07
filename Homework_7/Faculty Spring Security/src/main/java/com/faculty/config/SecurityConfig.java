package com.faculty.config;

import com.faculty.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.faculty")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // http builder configurations for authorize requests and form login (see below)
        http
                .authorizeRequests()
                .antMatchers("/education-faculty.com.ua/registration").permitAll()
                .antMatchers("/education-faculty.com.ua/login").permitAll()
                .antMatchers("/education-faculty.com.ua/user/**", "/education-faculty.com.ua/registration/**").hasRole("user")
                .antMatchers("/education-faculty.com.ua/admin", "/education-faculty.com.ua/admin/**").hasRole("admin").anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .loginPage("/education-faculty.com.ua/login")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/education-faculty.com.ua/logout"))
                .logoutSuccessUrl("/education-faculty.com.ua/login")
                .and()
                .exceptionHandling()
        .accessDeniedPage("/education-faculty.com.ua/attention-page");
    }
}
