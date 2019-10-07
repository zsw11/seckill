package com.dayup.seckil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dayup.seckil.model.User;

@Repository
public interface UserRpository extends JpaRepository<User, String>{

	public User findByUsernameAndPassword(String username,String Password);
	
	public User findByUsername(String username);
}
