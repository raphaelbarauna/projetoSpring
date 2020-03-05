package com.raphaelbarauna.projetoLoja.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.raphaelbarauna.projetoLoja.domain.enums.StatusPayment;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PaymentCard extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;
	
	public PaymentCard() {
		
	}

	public PaymentCard(Integer id, StatusPayment statusPayment, Order order, Integer numeroParcelas) {
		super(id, statusPayment, order);
		this.numeroParcelas = numeroParcelas;
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}

