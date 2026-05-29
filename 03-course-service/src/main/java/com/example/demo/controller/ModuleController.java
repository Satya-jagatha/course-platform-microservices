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

import com.example.demo.entity.CourseModules;
import com.example.demo.service.ModuleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/module")
public class ModuleController 
{
	private final ModuleService moduleService;
	
	public ModuleController(ModuleService moduleService)
	{
		this.moduleService = moduleService;
	}
	
	@GetMapping
	public List<CourseModules> findAll()
	{
		return moduleService.findAll();
	}
	
	@GetMapping("/{id}")
	public CourseModules findById(@PathVariable Long id)
	{
		return moduleService.findById(id);
	}
	
	@GetMapping("/course/{courseId}")
	public List<CourseModules> findByCourseId(@PathVariable Long courseId)
	{
		return moduleService.findByCourseId(courseId);
	}
	
	@PostMapping
	public CourseModules save(@Valid @RequestBody CourseModules module)
	{
		moduleService.saveOrUpdate(module);
		return module;
	}
	
	@PutMapping("/{id}")
	public CourseModules update(@Valid @RequestBody CourseModules module, @PathVariable Long id)
	{
		module.setId(id);
		moduleService.saveOrUpdate(module);
		return module;
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id)
	{
		moduleService.deleteById(id);
		return "module with id :"+id+" deleted successfully";
	}

}
