package com.example.carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarrosService;
import com.example.carros.domain.dto.CarroDTO;

@SpringBootTest
class CarrosServiceTests {

	@Autowired
	CarrosService service = new CarrosService();
	
	@Test
	void testeSave() {
		Carro c = new Carro();
		c.setNome("brasilia");
		c.setTipo("classicos");
		
		CarroDTO dto = service.insert(c);
		
		assertNotNull(dto);
		assertNotNull(dto.getId());
		
		CarroDTO op = service.getCarroById(dto.getId());
		assertNotNull(op);
		
		assertEquals(c.getNome(), op.getNome());
		assertEquals(c.getTipo(), op.getTipo());
		
		
		  service.delete(op.getId());
		  
		  try {
			  assertNull(service.getCarroById(op.getId()));
			  fail("O carro nao foi excluido");
		  } catch (ObjectNotFoundException e) {
			//OK
		}
		
	}
	@Test
	void testeLista() {
		List<CarroDTO> carros = service.getCarros();
		
		assertEquals(30, carros.size());
		
	}

}
