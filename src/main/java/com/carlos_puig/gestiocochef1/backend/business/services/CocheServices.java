package com.carlos_puig.gestiocochef1.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.carlos_puig.gestiocochef1.backend.business.model.*;

public interface CocheServices {

	Long create(CocheF1 producto);	    // C
	void delete(Long id);	
	void update(CocheF1 producto);	
	Optional<CocheF1> read(Long id);   // R
	
	List<CocheF1> getAll();
	
	List<CocheF1> findByEscuderia(Escuderia escuderia);
	
}
