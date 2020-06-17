package com.ss.training.utopia.orchestrator.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Trevor Huis in 't Veld
 * @author Justin O'Brien
 */
@Service
public class UserDetailsServiceClass implements UserDetailsService {

    @Autowired
	UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
		return new UserDetailsClass(
				user.orElseThrow(() -> new UsernameNotFoundException("The user '" + username + "' was not found.")));
    }
    
}