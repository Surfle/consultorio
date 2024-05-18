package com.consultorio.consultorio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultorio.consultorio.entity.User;


public interface LoginRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String login);
	
}
