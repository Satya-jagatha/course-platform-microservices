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

import com.example.demo.entity.Courses;
import com.example.demo.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController 
{
	private final CourseService courseService;
	public CourseController(CourseService courseService)
	{
		this.courseService = courseService;
	}
	
	@GetMapping
	public List<Courses> findAll()
	{
		return courseService.findAll();
	}
	
	@GetMapping("/{id}")
	public Courses findById(@PathVariable Long id)
	{
		return courseService.findById(id);
	}
	
	@GetMapping("/title/{title}")
	public Courses findByTitle(@PathVariable String title)
	{
		return courseService.findByTitle(title);
	}
	
	@GetMapping("/level/{level}")
	public List<Courses> findByLevel(@PathVariable String level)
	{
		return courseService.findByLevel(level);
	}
	
	@GetMapping("/active/{active}")
	public List<Courses> findByActive(@PathVariable Boolean active)
	{
		return courseService.findByActive(active);
	}
	
	@GetMapping("category/{categoryId}")
	public List<Courses> findByCategoryId(@PathVariable Long categoryId)
	{
		return courseService.findByCategoryId(categoryId);
	}
	
	@GetMapping("/category/name/{name}")
	public List<Courses> findByCategoryName(@PathVariable String name)
	{
		return courseService.findByCategoryName(name);
	}
	
	@PostMapping
	public Courses save(@Valid @RequestBody Courses course)
	{
		courseService.save(course);
		return course;
	}
	
	@PutMapping("/{id}")
	public Courses update(@Valid @RequestBody Courses course, @PathVariable Long id)
	{
		course.setId(id);
		return courseService.update(id, course);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id)
	{
		courseService.deleteById(id);
		return "course with the id : "+id+" was deleted successfully";
	}

}
