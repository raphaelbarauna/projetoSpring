package com.raphaelbarauna.projetoLoja.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.raphaelbarauna.projetoLoja.DTO.CustomerNewDTO;
import com.raphaelbarauna.projetoLoja.domain.Customer;
import com.raphaelbarauna.projetoLoja.domain.enums.TypeCustomer;
import com.raphaelbarauna.projetoLoja.repositories.CustomerRepository;
import com.raphaelbarauna.projetoLoja.resources.exception.FieldMessage;
import com.raphaelbarauna.projetoLoja.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override 
    public void initialize(CustomerInsert ann) {   
		
	}

@Override     
public boolean isValid(CustomerNewDTO objDto, ConstraintValidatorContext context) { 
		//clase criada para instanciar mensagem de error
        List<FieldMessage> list = new ArrayList<>(); 
        
        if(objDto.getType().equals(TypeCustomer.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj()) ) {
        	list.add(new FieldMessage("cpfOrCnpj", "CPF inválido."));
        }
        if(objDto.getType().equals(TypeCustomer.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj()) ) {
        	list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido."));
        }
        
        Customer aux = customerRepository.findByEmail(objDto.getEmail());
        
        if(aux != null) {
        	list.add(new FieldMessage("email", "Email já existente"));
        }
        // inserir errors personalizados para a lista do spring            
        for (FieldMessage e : list) {   
        	context.disableDefaultConstraintViolation();   
        	context.buildConstraintViolationWithTemplate(e.getMessage())   
        		.addPropertyNode(e.getFieldName()).addConstraintViolation();  
        	}  
        return list.isEmpty();   
        	
	} 
} 