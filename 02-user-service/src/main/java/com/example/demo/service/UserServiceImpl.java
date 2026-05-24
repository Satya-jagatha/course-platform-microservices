package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Users;

@Service
public class UserServiceImpl implements UserService
{
	
	private final UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo)
	{
		this.userRepo = userRepo;
	}

	
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	
	public Users findById(Long id) {
		// TODO Auto-generated method stub
		return userRepo.findById(id).orElseThrow();
	}

	
	public void save(Users user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
		
	}
	
	public Users update(Long id, Users user) {
		// TODO Auto-generated method stub
		Users existUser = userRepo.findById(id).orElseThrow();
		
		existUser.setName(user.getName());
		existUser.setEmail(user.getEmail());
		existUser.setPassword(user.getPassword());
		existUser.setRoleId(user.getRoleId());
		
		return userRepo.save(existUser);
		
		
		
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		userRepo.deleteById(id);
		
	}


	
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email).orElseThrow();
	}


	
	

}
