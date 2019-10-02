package com.raphaelbarauna.projetoLoja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.raphaelbarauna.projetoLoja.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Transactional(readOnly=true)
	Customer findByEmail(String email);
	
}
