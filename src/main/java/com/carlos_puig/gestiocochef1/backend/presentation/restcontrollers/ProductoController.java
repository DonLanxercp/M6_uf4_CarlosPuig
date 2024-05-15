package com.carlos_puig.gestiocochef1.backend.presentation.restcontrollers;

import com.carlos_puig.gestiocochef1.backend.business.model.CocheF1;
import com.carlos_puig.gestiocochef1.backend.business.services.ProductoServices;
import com.carlos_puig.gestiocochef1.backend.presentation.config.RespuestaError;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;
   	 
    @GetMapping
    public List<CocheF1> getAll(@RequestParam(name = "min", required = false) Double min,
   							  @RequestParam(name = "max", required = false) Double max){
   	 
   	 List<CocheF1> cochesF1 = null;
   	 
   	 if(min != null && max != null) {
   		cochesF1 = productoServices.getAll();
   	 } else {
   		cochesF1 = productoServices.getAll();
   	 }
   		 
   	 return cochesF1;
    }
	
}
