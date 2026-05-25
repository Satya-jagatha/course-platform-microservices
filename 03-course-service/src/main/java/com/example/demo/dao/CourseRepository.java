package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long>
{
	Optional<Courses> findByTitle(String title);

}
