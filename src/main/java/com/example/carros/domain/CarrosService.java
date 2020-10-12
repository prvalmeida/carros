package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.dto.CarroDTO;

import javassist.NotFoundException;

@Service
public class CarrosService {
	
	@Autowired
	private CarroRepository rep;
	
	public List<CarroDTO> getCarros() {
		return rep.findAll().stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
	}
	
	public CarroDTO getCarroById(Long id) {
		return rep.findById(id).map(c -> CarroDTO.create(c)).orElseThrow(() -> new ObjectNotFoundException("Carro nao encontrado"));
	}
	
	public List<CarroDTO> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
	}
	
	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId(), "Nao foi possivel inserir o registro");
		
		return CarroDTO.create(rep.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id, "Nao foi possivel atualizar o registro");
		
		Optional<Carro> c = rep.findById(id);
		if (c.isPresent()) {
			Carro db = c.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			
			rep.save(db);
			
			return CarroDTO.create(db);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		rep.deleteById(id);
	}
}
