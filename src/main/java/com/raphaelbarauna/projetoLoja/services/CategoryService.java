package com.raphaelbarauna.projetoLoja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphaelbarauna.projetoLoja.domain.Category;
import com.raphaelbarauna.projetoLoja.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Optional<Category> find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		
		
		return obj;
		
		
	}

}
