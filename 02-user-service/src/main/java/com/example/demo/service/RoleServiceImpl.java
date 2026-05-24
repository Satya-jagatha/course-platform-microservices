package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.entity.Roles;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class RoleServiceImpl implements RoleService
{
	
	private final RoleRepository roleRepo;
	
	public RoleServiceImpl(RoleRepository roleRepo)
	{
		this.roleRepo = roleRepo;
	}

	
	public List<Roles> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

	
	public Roles findById(Long id) {
		// TODO Auto-generated method stub
		return roleRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("role details was not found with the id :"+id));
	}

	
	public void saveOrUpdate(Roles role) {
		// TODO Auto-generated method stub
		roleRepo.save(role);
		
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		roleRepo.deleteById(id);
		
	}

}
