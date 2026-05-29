package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CourseModules;

public interface ModuleService 
{
	public List<CourseModules> findAll();
	public CourseModules findById(Long id);
	public void saveOrUpdate(CourseModules module);
	public void deleteById(Long id);
	public List<CourseModules> findByCourseId(Long courseId);

}
