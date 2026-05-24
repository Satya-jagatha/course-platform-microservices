package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Roles;

public interface RoleService 
{
	public List<Roles> findAll();
	public Roles findById(Long id);
	public void saveOrUpdate(Roles role);
	public void deleteById(Long id);

}
