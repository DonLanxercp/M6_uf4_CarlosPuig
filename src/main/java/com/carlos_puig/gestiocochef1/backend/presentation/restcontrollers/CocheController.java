package com.carlos_puig.gestiocochef1.backend.presentation.restcontrollers;

import com.carlos_puig.gestiocochef1.backend.business.model.CocheF1;
import com.carlos_puig.gestiocochef1.backend.business.model.Escuderia;
import com.carlos_puig.gestiocochef1.backend.business.services.CocheServices;
import com.carlos_puig.gestiocochef1.backend.presentation.config.RespuestaError;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/coches")
public class CocheController {

    @Autowired
    private CocheServices cocheServices;
   	 
    @GetMapping
    public List<CocheF1> getAll() {
        return cocheServices.getAll();
    }
    
    @GetMapping("/Escuderia")
    public List<CocheF1> findByEscuderia(@RequestParam Escuderia escuderia) {
        return cocheServices.findByEscuderia(escuderia);
    }
    
    
    @PostMapping
    public ResponseEntity<CocheF1> create(@RequestBody CocheF1 cocheF1, UriComponentsBuilder uriBuilder) {
        Long id = cocheServices.create(cocheF1);
        URI location = uriBuilder.path("/coches/{id}")
                                 .buildAndExpand(id)
                                 .toUri();
        return ResponseEntity.created(location).body(cocheF1);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CocheF1> update(@PathVariable Long id, @RequestBody CocheF1 cocheF1) {
        cocheF1.setId(id);
        cocheServices.update(cocheF1);
        return ResponseEntity.ok(cocheF1);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    		cocheServices.delete(id);
    		return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/coches/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		
		if(id > 500) {
			throw new RuntimeException("ID: " + id + " NO válido");
		}
		
		Optional<CocheF1> optional = cocheServices.read(id);
		
		if (optional.isEmpty()) {
			RespuestaError respuestaError = new RespuestaError("No se encuentra el producto con ID: " + id);
			return new ResponseEntity<>(respuestaError, HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(optional.get());
	}
    
    @ExceptionHandler({IllegalArgumentException.class, ClassCastException.class})
	public ResponseEntity<?> exceptionControl1(Exception e){
		return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionControl2(Exception e){
		return ResponseEntity.badRequest().body(new RespuestaError(e.getMessage()));
	}
	
}
