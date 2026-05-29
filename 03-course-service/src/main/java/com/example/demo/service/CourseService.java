package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Courses;

public interface CourseService 
{
	public List<Courses> findAll();
	public Courses findById(Long id);
	public Courses findByTitle(String title);
	public void save(Courses course);
	public Courses update(Long id, Courses course);
	public void deleteById(Long id);
	public List<Courses> findByLevel(String level);
	public List<Courses> findByActive(Boolean active);
	public List<Courses> findByCategoryId(Long categoryId);
	public List<Courses> findByCategoryName(String name);

}
