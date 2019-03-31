package com.mastercode.personalfinancialsystem.dto;

import com.mastercode.personalfinancialsystem.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

	private String nome;
	private String email;
	private String password;

	public User dtoToUser() {
		return new User(this.nome, this.email, this.password);
	}

}
