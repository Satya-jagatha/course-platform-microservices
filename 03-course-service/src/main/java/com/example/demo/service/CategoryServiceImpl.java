package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entity.CourseCategories;

@Service
public class CategoryServiceImpl implements CategoryService
{
	private final CategoryRepository categoryRepo;
	
	public CategoryServiceImpl(CategoryRepository categoryRepo)
	{
		this.categoryRepo = categoryRepo;
	}

	
	public List<CourseCategories> findAll() {
		// TODO Auto-generated method stub
		return categoryRepo.findAll();
	}

	
	public CourseCategories findById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepo.findById(id).orElseThrow();
	}

	
	public CourseCategories findByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepo.findByName(name).orElseThrow();
	}

	
	public void save(CourseCategories category) {
		// TODO Auto-generated method stub
		categoryRepo.save(category);
	}

	
	public CourseCategories update(Long id, CourseCategories category) {
		// TODO Auto-generated method stub
		CourseCategories existCategory = categoryRepo.findById(id).orElseThrow();
		existCategory.setCategory(category.getCategory());
		existCategory.setName(category.getName());
		existCategory.setId(existCategory.getId());
		return existCategory;
	}

	
	public void deleteById(Long id) 
	{
		// TODO Auto-generated method stub
		CourseCategories existCategory = categoryRepo.findById(id).orElseThrow();
		categoryRepo.deleteById(id);
		
	}

}
