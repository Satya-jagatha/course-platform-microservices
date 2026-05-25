package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CourseCategories;

public interface CategoryService 
{
	public List<CourseCategories> findAll();
	public CourseCategories findById(Long id);
	public CourseCategories findByName(String name);
	public void save(CourseCategories category);
	public CourseCategories update(Long id,CourseCategories category);
	public void deleteById(Long id);

}
