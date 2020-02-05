package com.leejuhyeok.board.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.leejuhyeok.board.service.CustomOAuth2UserService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // @EnableWebSecurity 애너테이션은 웹 보안을 활성화 한다. 하지만 그자체로는 유용하지 않고, 스프링 시큐리티가
					// WebSecurityConfigurer를 구현하거나 컨텍스트의 WebSebSecurityConfigurerAdapter를 확장한 빈으로
					// 설정되어 있어야 한다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.csrf().disable()
    	.headers().frameOptions().disable()
    	.and()
    	.authorizeRequests()
    	.antMatchers("/**","/css/**", "/images/**", "/js/**","/h2-console/**").permitAll()
    	.anyRequest().authenticated()
    	.and()
    	.logout().logoutSuccessUrl("/")
    	.and()
    	.oauth2Login()
    	.userInfoEndpoint()
    	.userService(customOAuth2UserService);
    	
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
