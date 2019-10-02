package com.raphaelbarauna.projetoLoja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphaelbarauna.projetoLoja.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer>{
	

}
