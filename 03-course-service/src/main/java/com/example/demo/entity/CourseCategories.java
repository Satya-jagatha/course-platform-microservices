package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="course_categories")
public class CourseCategories 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotNull(message="Name cannot be blank")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Courses> category;

	

	public CourseCategories() {
		
	}

	public CourseCategories(Long id, @NotNull(message = "Name cannot be blank") String name) {
		
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Courses> getCategory() {
		return category;
	}

	public void setCategory(Set<Courses> category) {
		this.category = category;
	}
	
	
	
	
	
	
	

}
