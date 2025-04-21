package com.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	private String resourceName;
	private String resourceField;
	private Integer userId;
	public ResourceNotFoundException(String resourceName, String resourceField, Integer userId) {
		super(String.format("Resource not found for Entity Operation %s, for field %s with value %s", resourceName, resourceField, userId));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.userId = userId;
	}
	
	
}
