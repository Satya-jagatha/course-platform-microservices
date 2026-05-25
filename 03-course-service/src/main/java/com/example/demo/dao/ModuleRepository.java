package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CourseModules;

@Repository
public interface ModuleRepository extends JpaRepository<CourseModules, Long>
{

}
