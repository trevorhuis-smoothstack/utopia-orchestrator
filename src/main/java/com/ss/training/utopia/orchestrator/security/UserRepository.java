package com.ss.training.utopia.orchestrator.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Trevor Huis in 't Veld
 * @author Justin O'Brien
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
