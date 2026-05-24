package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="users")
public class Users 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotBlank(message = "name cannot be blank")
	private String name;
	
	@Column(name="email")
	@NotBlank(message ="email cannot be blank")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne(targetEntity = Roles.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "role_id", insertable=false, updatable=false)
	private Roles userRole;
	
	public Users()
	{
		
	}

	public Users(Long id, String name, String email, String password, Long roleId, LocalDateTime createAt) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public LocalDateTime getCreateAt() {
		return createdAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
	

}
