package com.ss.training.utopia.orchestrator.counter.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ss.training.utopia.orchestrator.security.User;

/**
 * @author Justin O'Brien
 */
@Service
public class UserDetailsServiceClassCounter implements UserDetailsService {

    @Autowired
	UserRepositoryCounter userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
		return new UserDetailsClass(
				user.orElseThrow(() -> new UsernameNotFoundException("The user '" + username + "' was not found.")));
    }
    
}