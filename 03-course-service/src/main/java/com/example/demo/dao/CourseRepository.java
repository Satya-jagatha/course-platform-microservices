package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long>
{
	Optional<Courses> findByTitle(String title);
	List<Courses> findByLevel(String level);
	List<Courses> findByActive(Boolean active);
	List<Courses> findByCategoryId(Long categoryId);
	List<Courses> findByCategory_Name(String name);
}
