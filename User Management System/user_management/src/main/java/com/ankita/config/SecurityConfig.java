package com.ankita.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService getuserDetailsService() {
		return new UserDetailsServiceImple();
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncodder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider getDaoAuthProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getuserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncodder());
		
		return daoAuthenticationProvider;
	}
	
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/**").permitAll()
        )
        .formLogin(form -> form
                .loginPage("/signin") // Ensure /signin exists in your templates
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user/")
                .permitAll()
        )
        .csrf(csrf -> csrf.disable()); // Disable CSRF if required
        
        return http.build();
    }
}
