package com.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users") //for creating a table name with a different name than Java Class
@Getter
@Setter
@NoArgsConstructor
@Data
public class User {

	@Id //for setting pkey
	@GeneratedValue(strategy = GenerationType.AUTO) //for auto incrementing pKey
	private Integer id;
	@Column(name = "user_name", nullable = false, length = 100) //for changing column name, and setting other properties
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private String about;
	
}
