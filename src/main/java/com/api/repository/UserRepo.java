package com.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.entities.User;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
