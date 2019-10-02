package com.raphaelbarauna.projetoLoja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphaelbarauna.projetoLoja.domain.ItemOrder;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer>{
	

}
