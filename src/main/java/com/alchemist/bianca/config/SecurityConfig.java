package com.alchemist.bianca.config;

import com.alchemist.bianca.error.ExceptionHandlerFilter;
import com.alchemist.bianca.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .csrf().disable()
                .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //jwt 사용 시 stateless로 설정
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/reduplication/email/{email}").permitAll()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/reduplication/name/{name}").permitAll()
                .antMatchers(HttpMethod.POST, "/sms-certification/sends").permitAll()
                .antMatchers(HttpMethod.GET, "/sms-certification/confirms").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.PUT, "/refresh").permitAll()
                .anyRequest().authenticated()
                .and().apply(new FilterConfig(jwtTokenProvider, exceptionHandlerFilter));

    }
}
