package com.blog.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
	private Integer id;

	@NotEmpty
	@Size(min = 3, message = "Name must be of 3 characters !!")
	private String name;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Size(min = 3, max = 15, message = "Password must be in range of 3-15 characters !!")
	private String password;

	@NotEmpty
	private String about;

}
