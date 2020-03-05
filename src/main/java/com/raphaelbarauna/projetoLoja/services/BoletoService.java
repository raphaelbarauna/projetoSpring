package com.raphaelbarauna.projetoLoja.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.raphaelbarauna.projetoLoja.domain.PaymentBoleto;



@Service
public class BoletoService {
	
	public void preencherPaymentBoleto(PaymentBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDateExpiry(cal.getTime());
	}
	
	
}
	

