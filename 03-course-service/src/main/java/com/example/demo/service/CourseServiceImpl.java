package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CourseRepository;
import com.example.demo.entity.Courses;

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
		return courseRepo.findById(id).orElseThrow();
	}

	
	public Courses findByTitle(String title) {
		// TODO Auto-generated method stub
		return courseRepo.findByTitle(title).orElseThrow();
	}

	
	public void save(Courses course) 
	{
		// TODO Auto-generated method stub
	    courseRepo.save(course);
		
	}

	
	public Courses update(Long id, Courses course) {
		// TODO Auto-generated method stub
		Courses existCourse = courseRepo.findById(id).orElseThrow();
		existCourse.setTitle(course.getTitle());
		existCourse.setCategory(course.getCategory());
		existCourse.setDescription(course.getDescription());
		existCourse.setDurationHours(course.getDurationHours());
		existCourse.setLevel(course.getLevel());
		existCourse.setModules(course.getModules());
		existCourse.setActive(course.isActive());
		existCourse.setId(existCourse.getId());
		existCourse.setCreatedAt(existCourse.getCreatedAt());
		
		return existCourse;
	}

	
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		Courses course = courseRepo.findById(id).orElseThrow();
		
		courseRepo.deleteById(id);
		
		
	}

}
