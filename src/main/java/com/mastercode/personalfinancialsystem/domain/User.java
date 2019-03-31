package com.mastercode.personalfinancialsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@Email
	@NotBlank
	@Column(unique = true)
	private String email;

	@NotBlank
	private String password;

	public User(String nome, String email, String password) {
		this.nome = nome;
		this.email = email;
		this.password = password;
	}

}
