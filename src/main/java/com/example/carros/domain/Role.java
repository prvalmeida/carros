package com.example.carros.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Data
public class Role implements GrantedAuthority {
	
	@Id
	//a cada novo carro inserido, o ID vai ser incrementado automaticamente
	//JPA vai fazer o auto-incremento
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
}
