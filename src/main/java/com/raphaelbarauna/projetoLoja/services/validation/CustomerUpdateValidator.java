package com.raphaelbarauna.projetoLoja.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.raphaelbarauna.projetoLoja.DTO.CustomerDTO;
import com.raphaelbarauna.projetoLoja.domain.Customer;
import com.raphaelbarauna.projetoLoja.repositories.CustomerRepository;
import com.raphaelbarauna.projetoLoja.resources.exception.FieldMessage;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override 
    public void initialize(CustomerUpdate ann) {   
		
	}

@Override     
public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) { 
		
	@SuppressWarnings("unchecked")
	Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
	Integer uriId = Integer.parseInt(map.get("id"));
	//clase criada para instanciar mensagem de error
        List<FieldMessage> list = new ArrayList<>(); 
                     
        Customer aux = customerRepository.findByEmail(objDto.getEmail());
        //Verificar em caso de update se email ja existe
        if(aux != null && !aux.getId().equals(uriId)) {
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