package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Users;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;

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
		return userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user details not found with the id :"+id));
	}

	
	public void save(Users user) {
		// TODO Auto-generated method stub
		Optional<Users> existUser = userRepo
				.findByEmail(user.getEmail());
		if(existUser.isPresent())
		{
			throw new DuplicateResourceException("Email address already exist");
		}
		userRepo.save(user);
		
	}
	
	public Users update(Long id, Users user) {
		// TODO Auto-generated method stub
		Users existUser = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user details not found with the id :"+id));
		
		existUser.setName(user.getName());
		existUser.setEmail(user.getEmail());
		existUser.setPassword(user.getPassword());
		existUser.setRoleId(user.getRoleId());
		
		return userRepo.save(existUser);
		
		
		
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		Users user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("role details was not found with the id :"+id));
	
		userRepo.deleteById(id);
		
	}


	
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("user details with not found with the email :"+email));
	}


	
	

}
