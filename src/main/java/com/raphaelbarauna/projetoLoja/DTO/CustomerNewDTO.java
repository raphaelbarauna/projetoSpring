package com.raphaelbarauna.projetoLoja.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.raphaelbarauna.projetoLoja.services.validation.CustomerInsert;

@CustomerInsert
public class CustomerNewDTO implements Serializable{	
private static final long serialVersionUID = 1L; 

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 80 caracteres.")
	private String name;
	
	@Email(message = "E-mail inválido.")
	@NotEmpty(message="Preenchimento obrigatório.")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	private String cpfOrCnpj;
	private Integer type;	
	
	@NotEmpty(message="Preenchimento obrigatório.")
	private String street;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	private String number;
	
	private String complement;
	private String suburb;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	private String postalCode;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cityId;
	
	public CustomerNewDTO() {
		
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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
	
}
