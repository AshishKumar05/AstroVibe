package com.api.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.entities.Astrologer;
import com.api.enums.Status;


public interface AstrologerRepo extends JpaRepository<Astrologer,Integer>{
	Optional<Astrologer> findByEmail(String email);
	List<Astrologer> findByStatus(Status status);
	long countByIsVerifiedFalse();

}
