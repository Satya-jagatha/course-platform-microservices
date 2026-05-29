package com.example.demo.exception;

import java.time.LocalDateTime;

public class ApiError 
{
	private int status;
	private String name;
	private String error;
	private String path;
	private LocalDateTime timeStamp;
	
	public ApiError(int status, String name, String error, String path, LocalDateTime timeStamp) 
	{
		this.status = status;
		this.name = name;
		this.error = error;
		this.path = path;
		this.timeStamp = timeStamp;
	}
	
	

	public ApiError() {
		
	}



	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
	

}
