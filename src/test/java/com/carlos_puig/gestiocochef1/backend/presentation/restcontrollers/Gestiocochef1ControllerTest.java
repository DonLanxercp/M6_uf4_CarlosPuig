package com.carlos_puig.gestiocochef1.backend.presentation.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import com.carlos_puig.gestiocochef1.backend.business.model.CocheF1;
import com.carlos_puig.gestiocochef1.backend.business.model.Escuderia;
import com.carlos_puig.gestiocochef1.backend.business.services.impl.CocheServicesImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class Gestiocochef1ControllerTest {

	//@WebMvcTest(controllers=ProductoController.class);
	
	@Autowired
	private MockMvc miniPostman;
	
	@MockBean
	private CocheServicesImpl cocheServicesImpl;
	
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
		when(cocheServicesImpl.getAll()).thenReturn(cochesF1);
		
		MvcResult respuesta = miniPostman.perform(get("/cochesF1").contentType("application/json"))
				.andExpect(status().isOk())
				.andReturn();

	    String responseBody = respuesta.getResponse().getContentAsString();
	    String respuestaEsperada = objectMapper.writeValueAsString(cochesF1);
	    
	    assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
	}
	
	@Test
    void pedimos_coche_por_id() throws Exception {
        when(cocheServicesImpl.read(1L)).thenReturn(Optional.of(c1));

        MvcResult respuesta = miniPostman.perform(get("/coches/1").contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = respuesta.getResponse().getContentAsString();
        String respuestaEsperada = objectMapper.writeValueAsString(c1);

        assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaEsperada);
    }
	
	@Test
	void eliminar_coche() throws Exception {
	    when(cocheServicesImpl.read(1L)).thenReturn(Optional.of(c1));
	    doNothing().when(cocheServicesImpl).delete(1L);

	    miniPostman.perform(delete("/coches/1")
	            .contentType("application/json"))
	            .andExpect(status().isNoContent());
	}
	
	@Test
    public void testGetAllCoches() throws Exception {
        CocheF1 c1 = new CocheF1();
        c1.setId(1L);
        c1.setNombre("Coche 1");
        c1.setEscuderia(Escuderia.ASTON_MARTIN);
        c1.setTemporada("2024");
        c1.setDescripcion("Coche del Nano");

        CocheF1 c2 = new CocheF1();
        c2.setId(2L);
        c2.setNombre("Coche 2");
        c2.setEscuderia(Escuderia.FERRARI);
        c2.setTemporada("2022");
        c2.setDescripcion("We are checking");

        List<CocheF1> cochesF1 = Arrays.asList(c1, c2);

        when(cocheServicesImpl.getAll()).thenReturn(cochesF1);

        miniPostman.perform(get("/coches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Coche 1"))
                .andExpect(jsonPath("$[1].nombre").value("Coche 2"));
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
