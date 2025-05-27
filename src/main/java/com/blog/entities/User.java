package com.blog.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();
	
}
