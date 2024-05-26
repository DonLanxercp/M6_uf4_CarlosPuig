package com.carlos_puig.gestiocochef1.backend.presentation.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import com.carlos_puig.gestiocochef1.backend.business.model.CocheF1;
import com.carlos_puig.gestiocochef1.backend.business.model.Escuderia;
import com.carlos_puig.gestiocochef1.backend.business.services.impl.ProductoServicesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class Gestiocochef1ControllerTest {

	//@WebMvcTest(controllers=ProductoController.class);
	
	@Autowired
	private MockMvc miniPostman;
	
	@MockBean
	private ProductoServicesImpl productoServicesImpl;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	CocheF1 c1 = new CocheF1();
	CocheF1 c2 = new CocheF1();
	
	@BeforeEach
	void init() {
	    initObjects();
	}
	
	@Test
	void pedimos_todos_los_productos() throws Exception { 
		List<CocheF1> cochesF1 = Arrays.asList(c1, c2);
		when(productoServicesImpl.getAll()).thenReturn(cochesF1);
		
		MvcResult respuesta = miniPostman.perform(get("/cochesF1").contentType("application/json"))
				.andExpect(status().isOk())
				.andReturn();

	    String responseBody = respuesta.getResponse().getContentAsString();
	    String respuestaEsperada = objectMapper.writeValueAsString(cochesF1);
	    
	    assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	}
	
private void initObjects() {
		
		c1.setActivo(true);
		c1.setDescripcion("Coche del Nano");
		c1.setEscuderia(Escuderia.ASTON_MARTIN);
		c1.setFechaPresentacion(null);
		c1.setTemporada("2024");
		c1.setNombre("AMR24");
		
		c2.setActivo(false);
		c2.setDescripcion("We are cheking");
		c2.setEscuderia(Escuderia.FERRARI);
		c2.setFechaPresentacion(null);
		c2.setTemporada("2022");
		c2.setNombre("F1-75");
	}	
}
