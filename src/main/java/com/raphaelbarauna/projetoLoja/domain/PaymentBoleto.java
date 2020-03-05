package com.raphaelbarauna.projetoLoja.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.raphaelbarauna.projetoLoja.domain.enums.StatusPayment;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PaymentBoleto extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date dateExpiry;
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private Date datePayment;
	
	public PaymentBoleto() {
		
	}

	public PaymentBoleto(Integer id, StatusPayment statusPayment, Order order,Date dateExpiry, Date datePayment) {
		super(id, statusPayment, order);
		this.dateExpiry = dateExpiry;
		this.datePayment = datePayment;
	}

	public Date getDateExpiry() {
		return dateExpiry;
	}

	public void setDateExpiry(Date dateExpiry) {
		this.dateExpiry = dateExpiry;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}
	
	
	
}
