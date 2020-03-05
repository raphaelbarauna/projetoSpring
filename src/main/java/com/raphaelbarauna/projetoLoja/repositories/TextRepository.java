package com.raphaelbarauna.projetoLoja.repositories;

import com.raphaelbarauna.projetoLoja.domain.City;
import com.raphaelbarauna.projetoLoja.domain.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Integer>{
	

}
