package com.raphaelbarauna.projetoLoja.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.raphaelbarauna.projetoLoja.domain.ItemOrder;
import com.raphaelbarauna.projetoLoja.domain.Order;
import com.raphaelbarauna.projetoLoja.domain.PaymentBoleto;
import com.raphaelbarauna.projetoLoja.domain.enums.StatusPayment;
import com.raphaelbarauna.projetoLoja.repositories.CustomerRepository;
import com.raphaelbarauna.projetoLoja.repositories.ItemOrderRepository;
import com.raphaelbarauna.projetoLoja.repositories.OrderRepository;
import com.raphaelbarauna.projetoLoja.repositories.PaymentRepository;
import com.raphaelbarauna.projetoLoja.repositories.ProductRepository;
import com.raphaelbarauna.projetoLoja.services.exception.ObjectNotFoundException;



@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Order find(Integer id) {

		Optional<Order> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(

				"Objeto não encontrado! Id: " + id + ", Tipo: " +  Order.class.getName()));

	}
	
	@Transactional
	public Order insert(Order obj){
		
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCustomer(customerRepository.findById(obj.getCustomer().getId()).get());
		obj.getPayment().setStatus(StatusPayment.PENDING);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof PaymentBoleto) {
			PaymentBoleto pagto = (PaymentBoleto) obj.getPayment();
			boletoService.preencherPaymentBoleto(pagto, obj.getInstante());
		}
		obj =  repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemOrder ip : obj.getItems()) {
			ip.setDiscount(0.0);
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(productService.find(ip.getProduct().getId()).getPrice());
			ip.setOrder(obj);		
		}
		itemOrderRepository.saveAll(obj.getItems());
		return obj;
	}


}
