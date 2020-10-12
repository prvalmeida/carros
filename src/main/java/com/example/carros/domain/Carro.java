package com.example.carros.domain;

import javax.persistence.*;

import lombok.Data;

//quando a classe tem o mesmo nome da tabela, nao é necessário passar o nome para Entity
@Entity
@Data
public class Carro {
	
	@Id
	//a cada novo carro inserido, o ID vai ser incrementado automaticamente
	//JPA vai fazer o auto-incremento
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//como a coluna tambem chama 'nome', podemos deixar sem nada
	//caso contrario, deveria ser feito um mapeamento usando @Column
	private String nome;
	private String tipo;
	private String descricao;
	private String urlFoto;
	private String urlVideo;
	private String latitude;
	private String longitude;
	
	public Carro () {
		
	}
	
	public Carro(Long id, String nome, String tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}
}
