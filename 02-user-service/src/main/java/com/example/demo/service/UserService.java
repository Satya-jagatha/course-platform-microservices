package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Users;

public interface UserService 
{
	public List<Users> findAll();
	public Users findById(Long id);
	public void save(Users user);
	public Users update(Long id, Users user);
	public void deleteById(Long id);
	public Users findByEmail(String email);

}
