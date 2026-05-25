package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="courses")
public class Courses 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title")
	@NotNull(message="Title cannot be blank")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="level")
	private String level;
	
	@Column(name="duration_hours")
	private Integer durationHours;

	@Column(name="active")
	private Boolean active;

	

	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CourseCategories category;
	
	@JsonIgnore
	@OneToMany(mappedBy="course")
	private Set<CourseModules> modules;
	
	public Courses() {
		
	}
	public Courses(Long id, String title, String description, String level, Integer durationHours, Boolean active,
			 LocalDateTime createdAt) {
		
		this.id = id;
		this.title = title;
		this.description = description;
		this.level = level;
		this.durationHours = durationHours;
		this.active = active;
		this.createdAt = createdAt;
	}
	
	@PrePersist
	public void onCreate()
	{
		this.createdAt = LocalDateTime.now();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getDurationHours() {
		return durationHours;
	}
	public void setDurationHours(int durationHours) {
		this.durationHours = durationHours;
	}
	public Boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public CourseCategories getCategory() {
		return category;
	}
	public void setCategory(CourseCategories category) {
		this.category = category;
	}
	public Set<CourseModules> getModules() {
		return modules;
	}
	public void setModules(Set<CourseModules> modules) {
		this.modules = modules;
	}
	
	
	
	
	

}
