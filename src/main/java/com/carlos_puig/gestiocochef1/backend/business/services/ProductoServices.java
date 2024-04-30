package com.carlos_puig.gestiocochef1.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.carlos_puig.gestiocochef1.backend.business.model.*;

public interface ProductoServices {

	Long create(CocheF1 producto);	    // C
	Optional<CocheF1> read(Long id);   // R
	
	List<CocheF1> getAll();
	
}
