package com.mastercode.personalfinancialsystem.dto;

import com.mastercode.personalfinancialsystem.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String nome;
	private String email;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.nome = user.getNome();
		this.email = user.getEmail();
	}

	public User dtoToUser() {
		return new User(this.nome, this.email);
	}

}
