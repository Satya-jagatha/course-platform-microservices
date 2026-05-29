package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.entity.CourseCategories;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.ResourceNotFoundException;

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
		return categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with the id: "+id));
	}

	
	public CourseCategories findByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepo.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with the name : "+name));
	}

	
	public void save(CourseCategories category) {
		// TODO Auto-generated method stub
		Optional<CourseCategories> checkCategory = categoryRepo
				.findByName(category.getName());
		
		if(checkCategory.isPresent())
		{
			throw new DuplicateResourceException("Name already exist please provide a unique name");
		}
		
		categoryRepo.save(category);
	}

	
	public CourseCategories update(Long id, CourseCategories category) {
		// TODO Auto-generated method stub
		CourseCategories existCategory = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with the id :"+id));
		existCategory.setCategory(category.getCategory());
		existCategory.setName(category.getName());
		existCategory.setId(existCategory.getId());
		return existCategory;
	}

	
	public void deleteById(Long id) 
	{
		// TODO Auto-generated method stub
		CourseCategories existCategory = categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found with the id :"+id));
		categoryRepo.deleteById(id);
		
	}

}
