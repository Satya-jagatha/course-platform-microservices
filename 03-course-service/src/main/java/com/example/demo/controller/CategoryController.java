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

import com.example.demo.entity.CourseCategories;
import com.example.demo.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController 
{
	private final CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public List<CourseCategories> findAll()
	{
		return categoryService.findAll();
	}
	
	@GetMapping("/{id}")
	public CourseCategories findById(@PathVariable Long id)
	{
		return categoryService.findById(id);
	}
	
	@GetMapping("/name/{name}")
	public CourseCategories findByName(@PathVariable String name)
	{
		return categoryService.findByName(name);
	}
	
	@PostMapping
	public CourseCategories save(@Valid @RequestBody CourseCategories category)
	{
		categoryService.save(category);
		return category;
	}
	
	@PutMapping("/{id}")
	public CourseCategories update(@Valid @RequestBody CourseCategories category, @PathVariable Long id)
	{
		category.setId(id);
		return categoryService.update(id, category);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable Long id)
	{
		categoryService.deleteById(id);
		return "Category with the id :"+id+" was deleted successfully";
	}

}
