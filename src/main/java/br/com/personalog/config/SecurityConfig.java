package br.com.personalog.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@NonNull
	private UserDetailsService userDetails;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//super.configure(web);
		http.authorizeRequests()
			.antMatchers("/user/sign-up", "/sign-in/**", "/h2/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.and()
			.csrf().disable()
			.headers().frameOptions().disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(new BCryptPasswordEncoder());
	}
}
