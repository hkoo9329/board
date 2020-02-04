package com.leejuhyeok.board.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // @EnableWebSecurity 애너테이션은 웹 보안을 활성화 한다. 하지만 그자체로는 유용하지 않고, 스프링 시큐리티가 WebSecurityConfigurer를 구현하거나 컨텍스트의 WebSebSecurityConfigurerAdapter를 확장한 빈으로 설정되어 있어야 한다.
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //요청에 대한 권한을 지정할 수 있게 함
                .antMatchers("/","/css/**", "/images/**", "/js/**")
                .permitAll()
                .antMatchers("/google")
                .hasAnyAuthority() // 특정 권한을 가지는 사용자만 접근할 수 있다.
                .antMatchers("/kakao").hasAnyAuthority()
                .antMatchers("/github").hasAnyAuthority()
                .anyRequest().
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
