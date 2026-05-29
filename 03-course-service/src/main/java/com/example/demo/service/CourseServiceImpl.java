package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.entity.Courses;
import com.example.demo.exception.ResourceNotFoundException;

@Service
public class CourseServiceImpl implements CourseService
{
	private final CourseRepository courseRepo;
	
	public CourseServiceImpl(CourseRepository courseRepo)
	{
		this.courseRepo = courseRepo;
	}

	
	public List<Courses> findAll() {
		// TODO Auto-generated method stub
		return courseRepo.findAll();
	}

	
	public Courses findById(Long id) {
		// TODO Auto-generated method stub
		return courseRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with the id : "+id));
	}

	
	public Courses findByTitle(String title) {
		// TODO Auto-generated method stub
		return courseRepo.findByTitle(title)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with the title : "+title));
	}

	
	public void save(Courses course) 
	{
		// TODO Auto-generated method stub
	    courseRepo.save(course);
		
	}

	
	public Courses update(Long id, Courses course) {
		// TODO Auto-generated method stub
		Courses existCourse = courseRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with the id : "+id));
		existCourse.setTitle(course.getTitle());
		existCourse.setCategory(course.getCategory());
		existCourse.setDescription(course.getDescription());
		existCourse.setDurationHours(course.getDurationHours());
		existCourse.setLevel(course.getLevel());
		existCourse.setModules(course.getModules());
		existCourse.setActive(course.isActive());
		
		return courseRepo.save(existCourse);
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		Courses course = courseRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found with the id : "+id));
		
		courseRepo.deleteById(id);
		
		
	}



	public List<Courses> findByLevel(String level) {
		// TODO Auto-generated method stub
		return courseRepo.findByLevel(level);
	}


	
	public List<Courses> findByActive(Boolean active) {
		// TODO Auto-generated method stub
		return courseRepo.findByActive(active);
	}


	
	public List<Courses> findByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return courseRepo.findByCategoryId(categoryId);
	}


	
	public List<Courses> findByCategoryName(String name) {
		// TODO Auto-generated method stub
		return courseRepo.findByCategory_Name(name);
	}

}
