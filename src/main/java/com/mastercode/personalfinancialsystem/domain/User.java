package com.mastercode.personalfinancialsystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	
	public User() {		
	}
	
	public User(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	

}
