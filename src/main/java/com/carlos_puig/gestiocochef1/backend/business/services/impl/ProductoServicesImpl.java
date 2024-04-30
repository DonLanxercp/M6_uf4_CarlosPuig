package com.carlos_puig.gestiocochef1.backend.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.carlos_puig.gestiocochef1.backend.business.model.*;
import com.carlos_puig.gestiocochef1.backend.business.services.*;

@Service
public class ProductoServicesImpl implements ProductoServices {

	private final TreeMap<Long, CocheF1> PRODUCTOS = new TreeMap<>();
	
	public ProductoServicesImpl() {
		init();
	}
	
	@Override
	public Long create(CocheF1 producto) {
		
		Long id = PRODUCTOS.lastKey() + 1;
		
		producto.setId(id);
		
		PRODUCTOS.put(producto.getId(), producto);
		
		return id;
	}

	@Override
	public Optional<CocheF1> read(Long id) {
		return Optional.ofNullable(PRODUCTOS.get(id));
	}

	@Override
	public List<CocheF1> getAll() {
		return new ArrayList<>(PRODUCTOS.values());
	}
	
	// ***************************************************************
	//
	// Private Methods
	//
	// ***************************************************************

	private void init() {
		
		CocheF1 p1 = new CocheF1();
		CocheF1 p2 = new CocheF1();
		CocheF1 p3 = new CocheF1();
		
		p1.setId(10L);
		p1.setNombre("AMR24");
		p1.setEscuderia(Escuderia.ASTON_MARTIN);
		p1.setDescripcion("Cacharro para 2024 de Aston Martin");
		p1.setFechaPresentacion(new Date(100000100L));
		p1.setTemporada("2024");
		p1.setActivo(true);
		
		p2.setId(10L);
		p2.setNombre("AMR24");
		p2.setEscuderia(Escuderia.ASTON_MARTIN);
		p2.setDescripcion("Cacharro para 2024 de Aston Martin");
		p2.setFechaPresentacion(new Date(100000100L));
		p2.setTemporada("2024");
		p2.setActivo(true);
		
		p3.setId(10L);
		p3.setNombre("AMR24");
		p3.setEscuderia(Escuderia.ASTON_MARTIN);
		p3.setDescripcion("Cacharro para 2024 de Aston Martin");
		p3.setFechaPresentacion(new Date(100000100L));
		p3.setTemporada("2024");
		p3.setActivo(true);
		
		
		PRODUCTOS.put(p1.getId(), p1);
		PRODUCTOS.put(p2.getId(), p2);
		PRODUCTOS.put(p3.getId(), p3);
		
	}
}
