package com.example.demo.exception;

import java.time.LocalDateTime;

public class ApiError 
{
	private int status;
	private String error;
	private String name;
	private String path;
	private LocalDateTime timestamp;
	public ApiError() {
		
	}
	public ApiError(int status, String error, String name, String path, LocalDateTime timestamp) {
		super();
		this.status = status;
		this.error = error;
		this.name = name;
		this.path = path;
		this.timestamp = timestamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
