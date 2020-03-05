package com.raphaelbarauna.projetoLoja.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.raphaelbarauna.projetoLoja.domain.Customer;
import com.raphaelbarauna.projetoLoja.services.validation.CustomerUpdate;

@CustomerUpdate
public class CustomerDTO implements Serializable{	
private static final long serialVersionUID = 1L; 

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 80 caracteres.")
	private String name;
	
	@Email(message = "E-mail inválido.")
	@NotEmpty(message="Preenchimento obrigatório.")
	private String email;
	
	
	public CustomerDTO() {
		
	}
	
	public CustomerDTO(Customer obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
		
	}	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
