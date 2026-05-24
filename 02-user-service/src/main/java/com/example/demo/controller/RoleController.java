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

import com.example.demo.entity.Roles;
import com.example.demo.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController 
{
	private final RoleService roleService;
	
	public RoleController(RoleService roleService)
	{
		this.roleService = roleService;
	}
	
	@GetMapping
	public List<Roles> findAll()
	{
		return roleService.findAll();
	}
	
	@GetMapping("/{id}")
	public Roles findById(@PathVariable Long id)
	{
		return roleService.findById(id);
	}
	
	@PostMapping
	public Roles save(@Valid @RequestBody Roles role)
	{
		
	    roleService.saveOrUpdate(role);
		return role;
	}
	
	@PutMapping("/{id}")
	public Roles update(@Valid @PathVariable Long id,@RequestBody Roles role)
	{
		role.setId(id);
	    roleService.saveOrUpdate(role);
		return role;
	}
	
	@DeleteMapping("/{id}")
	public String DeleteById(@PathVariable Long id)
	{
		roleService.deleteById(id);
		return "Record with Id : "+id+" was deleted successfully.";
	}
	
	

}
