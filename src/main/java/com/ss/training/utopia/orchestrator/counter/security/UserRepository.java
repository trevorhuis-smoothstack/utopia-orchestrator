package com.ss.training.utopia.orchestrator.counter.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.training.utopia.orchestrator.security.User;


/**
 * @author Justin O'Brien
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
