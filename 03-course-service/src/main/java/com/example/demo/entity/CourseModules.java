package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="course_modules")
public class CourseModules 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	@NotNull(message="module title cannot be blank")
	private String title;
	
	@Column(name="video_url")
	private String videoUrl;
	
	@Column(name="module_order")
	private Integer moduleOrder;
	
	@Column(name="duration_minutes")
	private Integer durationMinutes;
	
	
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Courses course;

	public CourseModules() {
		
	}

	public CourseModules(Long id,String title, String videoUrl,
			Integer moduleOrder, Integer durationMinutes) {
		
		this.id = id;
		this.title = title;
		this.videoUrl = videoUrl;
		this.moduleOrder = moduleOrder;
		this.durationMinutes = durationMinutes;
		
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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(int moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}
	
	
	

}
