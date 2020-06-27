package com.ss.training.utopia.orchestrator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Trevor Huis in 't Veld
 */
@Configuration
@Order(2)
@EnableWebSecurity
public class SecurityConfigurationAgent extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceClass userDetailsService;

	@Autowired
	UserRepository userRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(new AuthenticationFilter(authenticationManager()))
				.addFilter(new AuthorizationFilter(authenticationManager(), this.userRepository)).authorizeRequests()
				.antMatchers("/agent/*").hasRole("AGENT").and().httpBasic();
	}

	/**
	 * @return
	 */
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
		return daoAuthenticationProvider;
	}

	/**
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}