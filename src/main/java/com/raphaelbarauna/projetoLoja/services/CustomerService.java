package com.raphaelbarauna.projetoLoja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raphaelbarauna.projetoLoja.DTO.CustomerDTO;
import com.raphaelbarauna.projetoLoja.DTO.CustomerNewDTO;
import com.raphaelbarauna.projetoLoja.domain.Adress;
import com.raphaelbarauna.projetoLoja.domain.City;
import com.raphaelbarauna.projetoLoja.domain.Customer;
import com.raphaelbarauna.projetoLoja.domain.enums.TypeCustomer;
import com.raphaelbarauna.projetoLoja.repositories.AdressRepository;
import com.raphaelbarauna.projetoLoja.repositories.CityRepository;
import com.raphaelbarauna.projetoLoja.repositories.CustomerRepository;
import com.raphaelbarauna.projetoLoja.services.exception.DataIntegrityException;
import com.raphaelbarauna.projetoLoja.services.exception.ObjectNotFoundException;



@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	@Autowired
	private AdressRepository adressRepository;
	@Autowired
	private CityRepository cityRepository;
	
	
	public Customer find(Integer id) {

		Optional<Customer> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto não encontrado! Id: " + id + ", Tipo: " +  Customer.class.getName()));

	}
	
	@Transactional
	public Customer insert(Customer obj){
		
		obj.setId(null);
		obj = repo.save(obj);
		adressRepository.saveAll(obj.getAdresses());
		return (obj);
	}

	public Customer update(Customer obj){		
		Customer newObj = find(obj.getId());		
		updateData(newObj, obj);		
		return repo.save(newObj);
	}
	
	private void updateData(Customer newObj, Customer obj) {		
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());		
	}

	public void delete(Integer id){
		
		try {
			find(id);
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao é possivel excluir um cliente que possui pedidos.");
		}				
	}
	public List<Customer> findAll(){
		
		return repo.findAll();
	}
	
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Customer fromDTO(CustomerDTO objDto) {
		
		return new Customer(objDto.getId(), objDto.getName(),objDto.getEmail(), null, null);
	}
	
	public Customer fromDTO(CustomerNewDTO objDto) {
		Customer cust = new Customer(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(),TypeCustomer.toEnum(objDto.getType()));
		Optional<City> cit = cityRepository.findById(objDto.getCityId());
		Adress adres = new Adress(null, objDto.getStreet(), objDto.getNumber(),objDto.getComplement(), objDto.getSuburb(), objDto.getPostalCode(), cust, cit.get());
		cust.getAdresses().add(adres);
		cust.getMobiles().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null) {
			cust.getMobiles().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3()!=null) {
			cust.getMobiles().add(objDto.getTelefone3());
		}
		
		return cust;
	}

}
