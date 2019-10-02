package com.raphaelbarauna.projetoLoja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raphaelbarauna.projetoLoja.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	

}
