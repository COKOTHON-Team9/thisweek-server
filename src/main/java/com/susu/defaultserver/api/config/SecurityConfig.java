package com.susu.defaultserver.api.config;

import com.susu.defaultserver.api.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests() // URL별 권한 관리 시작점
            .antMatchers("/", "/css/", "/images/**", "/js/**", "/h2/**", "/profile").permitAll()
            .antMatchers("/api/v1/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout()
            .logoutSuccessUrl("/"); // 로그아웃 성공시 "/" 이동
    }
}
