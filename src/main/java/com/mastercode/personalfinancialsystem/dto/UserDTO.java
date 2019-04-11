package com.mastercode.personalfinancialsystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.mastercode.personalfinancialsystem.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	@NotBlank
	@Size(min = 1)
	private String name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 6, max = 25)
	private String password;

	public User dtoToUser() {
		return new User(this.name, this.email, this.password);
	}

}
