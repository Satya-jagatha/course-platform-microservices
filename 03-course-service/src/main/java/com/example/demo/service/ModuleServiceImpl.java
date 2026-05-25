package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ModuleRepository;
import com.example.demo.entity.CourseModules;

@Service
public class ModuleServiceImpl implements ModuleService
{
	private final ModuleRepository moduleRepo;
	
	public ModuleServiceImpl(ModuleRepository moduleRepo)
	{
		this.moduleRepo = moduleRepo;
	}

	
	public List<CourseModules> findAll() {
		// TODO Auto-generated method stub
		return moduleRepo.findAll();
	}

	
	public CourseModules findById(Long id) {
		// TODO Auto-generated method stub
		return moduleRepo.findById(id).orElseThrow();
	}

	
	public void saveOrUpdate(CourseModules module) {
		// TODO Auto-generated method stub
		moduleRepo.save(module);
		
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		CourseModules module = moduleRepo.findById(id).orElseThrow();
		
		moduleRepo.deleteById(id);
		
	}

}
