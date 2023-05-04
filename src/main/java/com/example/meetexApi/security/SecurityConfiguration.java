package com.example.meetexApi.security;

import com.example.meetexApi.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/users/register", "/api/users/login", "/api/users/{userId}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/posts").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/posts/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/posts/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/posts/**/like").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/posts/*/unlike").authenticated()
                .antMatchers(HttpMethod.POST, "/api/posts/*/comments").authenticated()
                .antMatchers("/api/comments/**").authenticated()
                .antMatchers("/api/users/*/friend-requests/**").authenticated()
                .antMatchers("/api/posts/**/comments").permitAll()
                .antMatchers("/api/comments/**").authenticated()
                .antMatchers("/api/users/*/friend-requests/**").authenticated()
                .antMatchers("/api/users/*/accept-friend-request/**").authenticated()
                .antMatchers("/api/users/*/delete-friend-request/**").authenticated()
                .antMatchers("/api/users/*/friends").authenticated()
                .antMatchers(HttpMethod.POST, "/api/communities").authenticated()
                .antMatchers(HttpMethod.GET, "/api/communities/**").permitAll()
                .antMatchers("/api/posts/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}