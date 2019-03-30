/**
 * 
 */
package com.winwithweb.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author sachingoyal
 *
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/css/**","/js/**","/images/**").permitAll()
			.antMatchers("/home/**").hasAnyRole("USER")
			.anyRequest().authenticated()
        .and()
        .formLogin()
			.loginPage("/login").defaultSuccessUrl("/home")
			.permitAll()
			.and()
        .logout()
			.permitAll()
			.and()
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler);	
	}
	
	 @Bean
		public PasswordEncoder passwordEncoder(){
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication().passwordEncoder(passwordEncoder())
			.withUser("user").password("$2a$10$qO1gCcubvoHMXI3SBLgLJu3FnmGM96ANGQaBSC9dlkLCUWgkVUOFK").roles("USER");
	}

}
