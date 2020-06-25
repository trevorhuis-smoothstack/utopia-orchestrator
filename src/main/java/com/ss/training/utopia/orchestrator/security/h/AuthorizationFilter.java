package com.ss.training.utopia.orchestrator.security.h;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.ss.training.utopia.orchestrator.security.JwtProperties;
import com.ss.training.utopia.orchestrator.security.User;
import com.ss.training.utopia.orchestrator.security.UserDetailsClass;
import com.ss.training.utopia.orchestrator.security.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * @author Trevor Huis in 't Veld
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

	private UserRepository userRepository;

	/**
	 * @param authenticationManager
	 * @param userRepository
	 */
	public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		Authentication authentication;
		if (header != null && header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			authentication = getUsernamePasswordAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request)
			throws UsernameNotFoundException {
		String token = request.getHeader(JwtProperties.HEADER_STRING), username;
		UsernamePasswordAuthenticationToken authToken;
		User user;
		UserDetailsClass userDetails;
		if (token != null) {
			username = JWT.require(HMAC512(JwtProperties.SECRET.getBytes())).build()
					.verify(token.replace(JwtProperties.TOKEN_PREFIX, "")).getSubject();
			if (username != null) {
				user = userRepository.findByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("The user '" + username + "' was not found."));
				userDetails = new UserDetailsClass(user);
				authToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
				return authToken;
			}
		}
		return null;
	}

}