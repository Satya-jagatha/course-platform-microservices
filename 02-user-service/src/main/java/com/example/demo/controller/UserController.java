package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Users;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController 
{
	private final UserService usService;
	
	public UserController(UserService usService)
	{
		this.usService = usService;
	}
	
	@GetMapping
	public List<Users> findAll()
	{
		return usService.findAll();
	}
	
	@GetMapping("/{id}")
	public Users findById(@PathVariable Long id)
	{
		return usService.findById(id);
	}
	
	@GetMapping("/email/{email}")
	public Users findByEmail(@PathVariable String email)
	{
		return usService.findByEmail(email);
	}
	
	@PostMapping
	public Users save(@Valid @RequestBody Users u)
	{
		usService.save(u);
		return u;
	}
	
	@PutMapping("/{id}")
	public Users update(@Valid @RequestBody Users u,@PathVariable Long id)
	{
		
		usService.update(id,u);
		return u;
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id)
	{
		usService.deleteById(id);
		return "user with the id : "+id+" was deleted successfully";
	}
	
	

}
