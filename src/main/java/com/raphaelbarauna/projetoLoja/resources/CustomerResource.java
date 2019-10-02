package com.raphaelbarauna.projetoLoja.resources;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.raphaelbarauna.projetoLoja.DTO.CustomerDTO;
import com.raphaelbarauna.projetoLoja.DTO.CustomerNewDTO;
import com.raphaelbarauna.projetoLoja.domain.Customer;
import com.raphaelbarauna.projetoLoja.services.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomerResource {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Customer> find(@PathVariable Integer id ) {		
	//ResponseEntity encapsula informações para uma resposta HTTP/REST	
		Customer obj = service.find(id);		
 		
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDTO objDto){
		Customer obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CustomerDTO objDto, @PathVariable Integer id){
		Customer obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@RequestBody Customer obj, @PathVariable Integer id){
				
		service.delete(id);
		
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> findAll() {		
		
		List<Customer> list = service.findAll();
		//funcao para separar as categorias dos produtos
		//.stream percorre a lista
		//.collect faz voltar para o modo lista
		List<CustomerDTO> listDto = list.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CustomerDTO>> findPage(
			@RequestParam(value="page",defaultValue="0")Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {		
		
		Page<Customer> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CustomerDTO> listDto = list.map(obj -> new CustomerDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
