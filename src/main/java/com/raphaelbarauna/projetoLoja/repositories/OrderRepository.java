package com.raphaelbarauna.projetoLoja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphaelbarauna.projetoLoja.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	

}
