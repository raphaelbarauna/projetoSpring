package com.raphaelbarauna.projetoLoja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphaelbarauna.projetoLoja.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	

}
