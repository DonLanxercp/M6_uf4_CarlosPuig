package com.carlos_puig.gestiocochef1.backend.business.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlos_puig.gestiocochef1.backend.business.model.*;
import com.carlos_puig.gestiocochef1.backend.business.services.*;
import com.carlos_puig.gestiocochef1.backend.repositories.GestioCocheRepository;

import jakarta.transaction.*;

@Service
public class CocheServicesImpl implements CocheServices {

	@Autowired  // injectem el repositori
	private GestioCocheRepository gestioCocheRepository;
	
	@Override
	@Transactional // quan es fan modificacions a la BD
	public Long create(CocheF1 cocheF1) { 
		
		if(cocheF1.getId() != null) {
			throw new IllegalStateException("El id se agrega automáticamente, no se debe colocar de forma manual");
		}
		
		Long id = System.currentTimeMillis();
		cocheF1.setId(id);

	//cridem al mètode save() ja disponible al repositori	
		gestioCocheRepository.save(cocheF1); 
		return id;
	}
	@Override
	public Optional<CocheF1> read(Long id) {	
		return gestioCocheRepository.findById(id);
	}
	
	@Override
	public List<CocheF1> getAll() {
		return gestioCocheRepository.findAll();
	}
	@Override
	public List<CocheF1> findByEscuderia(Escuderia escuderia) {
		return gestioCocheRepository.findByEscuderia(escuderia);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		gestioCocheRepository.deleteById(id);
	}
	@Override
	@Transactional
	public void update(CocheF1 cochef1) {
		gestioCocheRepository.save(cochef1);
	}
}
