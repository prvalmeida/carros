package com.example.carros.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carros.domain.dto.CarroDTO;

public interface CarroRepository extends JpaRepository<Carro, Long>{

	List<Carro> findByTipo(String tipo);

}
