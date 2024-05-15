package com.carlos_puig.gestiocochef1.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carlos_puig.gestiocochef1.backend.business.model.CocheF1;
import com.carlos_puig.gestiocochef1.backend.business.model.Escuderia;

public interface GestioCocheRepository extends JpaRepository<CocheF1, Long>{
	  
		List<CocheF1> findByEscuderiaOrderByNombreAsc(String escuderia);
		
		@Query("SELECT c FROM CocheF1 c WHERE c.escuderia = :escuderia")
		List<CocheF1> findDescatalogadosByEescuderia(Escuderia escuderia);
	}
