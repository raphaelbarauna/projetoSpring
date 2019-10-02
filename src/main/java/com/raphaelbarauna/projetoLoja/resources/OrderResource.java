package com.raphaelbarauna.projetoLoja.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raphaelbarauna.projetoLoja.domain.Order;
import com.raphaelbarauna.projetoLoja.services.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Integer id ) {		
	//ResponseEntity encapsula informações para uma resposta HTTP/REST	
		Order obj = service.find(id);		
 		
		return ResponseEntity.ok().body(obj);
	}

}
