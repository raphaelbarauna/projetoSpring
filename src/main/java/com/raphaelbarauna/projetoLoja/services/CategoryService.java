package com.raphaelbarauna.projetoLoja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.raphaelbarauna.projetoLoja.DTO.CategoryDTO;
import com.raphaelbarauna.projetoLoja.domain.Category;
import com.raphaelbarauna.projetoLoja.domain.Customer;
import com.raphaelbarauna.projetoLoja.repositories.CategoryRepository;
import com.raphaelbarauna.projetoLoja.services.exception.DataIntegrityException;
import com.raphaelbarauna.projetoLoja.services.exception.ObjectNotFoundException;




@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {

		Optional<Category> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));

	}
	
	public Category insert(Category obj){
		obj.setId(null);
		return repo.save(obj);
	}

	public Category update(Category obj){		
		Category newObj = find(obj.getId());		
		updateData(newObj, obj);		
		return repo.save(newObj);
	}
	
	private void updateData(Category newObj, Category obj) {
		
		newObj.setNome(obj.getNome());
		
	}
	
	public void delete(Integer id){
		try {
			find(id);
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao é possivel excluir uma categoria que possui produtos.");
		}				
	}
	public List<Category> findAll(){
		
		return repo.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDto) {
		
		return new Category(objDto.getId(), objDto.getNome());
	}
}
