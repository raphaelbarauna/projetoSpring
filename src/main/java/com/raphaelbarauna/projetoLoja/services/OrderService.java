package com.raphaelbarauna.projetoLoja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.raphaelbarauna.projetoLoja.domain.Order;
import com.raphaelbarauna.projetoLoja.repositories.OrderRepository;
import com.raphaelbarauna.projetoLoja.services.exception.ObjectNotFoundException;



@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	public Order find(Integer id) {

		Optional<Order> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto não encontrado! Id: " + id + ", Tipo: " +  Order.class.getName()));

	}

}
