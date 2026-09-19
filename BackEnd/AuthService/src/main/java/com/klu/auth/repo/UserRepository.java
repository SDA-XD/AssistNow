package com.klu.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.auth.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}