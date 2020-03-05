package com.raphaelbarauna.projetoLoja.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raphaelbarauna.projetoLoja.domain.Adress;
import com.raphaelbarauna.projetoLoja.domain.Category;
import com.raphaelbarauna.projetoLoja.domain.City;
import com.raphaelbarauna.projetoLoja.domain.Customer;
import com.raphaelbarauna.projetoLoja.domain.ItemOrder;
import com.raphaelbarauna.projetoLoja.domain.Order;
import com.raphaelbarauna.projetoLoja.domain.Payment;
import com.raphaelbarauna.projetoLoja.domain.PaymentBoleto;
import com.raphaelbarauna.projetoLoja.domain.PaymentCard;
import com.raphaelbarauna.projetoLoja.domain.Product;
import com.raphaelbarauna.projetoLoja.domain.State;
import com.raphaelbarauna.projetoLoja.domain.enums.StatusPayment;
import com.raphaelbarauna.projetoLoja.domain.enums.TypeCustomer;
import com.raphaelbarauna.projetoLoja.repositories.AdressRepository;
import com.raphaelbarauna.projetoLoja.repositories.CategoryRepository;
import com.raphaelbarauna.projetoLoja.repositories.CityRepository;
import com.raphaelbarauna.projetoLoja.repositories.CustomerRepository;
import com.raphaelbarauna.projetoLoja.repositories.ItemOrderRepository;
import com.raphaelbarauna.projetoLoja.repositories.OrderRepository;
import com.raphaelbarauna.projetoLoja.repositories.PaymentRepository;
import com.raphaelbarauna.projetoLoja.repositories.ProductRepository;
import com.raphaelbarauna.projetoLoja.repositories.StateRepository;

@Service
public class DbService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AdressRepository adressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;
	
	
	public void instantiateDatabase() throws ParseException {

		
		Category cat1 = new Category(null,"informatica");
		Category cat2 = new Category(null,"escritorio");
		Category cat3 = new Category(null,"Cama mesa e banho");
		Category cat4 = new Category(null,"Eletronicos");
		Category cat5 = new Category(null,"Jardinagem");
		Category cat6 = new Category(null,"Decoracao");
		Category cat7 = new Category(null,"Perfumaria");
		
		
		Product p1 = new Product(null, "computador", 2000.00);
		Product p2 = new Product(null, "impressora", 800.00);
		Product p3 = new Product(null, "mouse", 80.00);
		Product p4 = new Product(null, "mesa de escritorio", 300.00);
		Product p5 = new Product(null, "toalha", 50.00);
		Product p6 = new Product(null, "colcha", 200.00);
		Product p7 = new Product(null, "tv true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		
			
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2,p4));
		cat3.getProducts().addAll(Arrays.asList(p5,p6));
		cat4.getProducts().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9,p10));
		cat5.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1,cat4));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategories().addAll(Arrays.asList(cat1,cat4));	
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));	
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));	
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));	
				
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		
		State state1 = new State(null, "Bahia");
		State state2 = new State(null, "Sao Paulo");
		
		City c1 = new City(null, "Salvador", state1);
		City c2 = new City(null, "Feira de Santana", state1);
		City c3 = new City(null, "Sao Paulo", state2);	
				
		state1.getCities().addAll(Arrays.asList(c1,c2));
		state2.getCities().addAll(Arrays.asList(c3));
				
		stateRepository.saveAll(Arrays.asList(state1,state2));
		cityRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Customer cus1 = new Customer(null, "Maria Silva", "margia@gmail.com","0451900355", TypeCustomer.PESSOAFISICA);
		cus1.getMobiles().addAll(Arrays.asList("0451900344","93379997"));
		Adress adr1 = new Adress(null, "Rua Flores","300","Apt101", "Jardim", "33744400", cus1, c1);
		Adress adr2 = new Adress(null, "Princes Highway","200","Apt303", "Jardim", "353000100", cus1, c2);
		
		cus1.getAdresses().addAll(Arrays.asList(adr1,adr2));
		
		customerRepository.saveAll(Arrays.asList(cus1));
		adressRepository.saveAll(Arrays.asList(adr1,adr2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cus1, adr1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cus1, adr2);
		
		Payment pagto1 = new PaymentCard(null, StatusPayment.PAID, ped1, 6);
		ped1.setPayment(pagto1);
		
		Payment pagto2 = new PaymentBoleto(null, StatusPayment.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);
		
		cus1.getOrders().addAll(Arrays.asList(ped1, ped2));
				
		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
	    ItemOrder iO1 = new ItemOrder(ped1, p1, 0.00, 1, 2000.00 );
		ItemOrder iO2 = new ItemOrder(ped1, p3, 0.00, 2, 80.00);
		ItemOrder iO3 = new ItemOrder(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItems().addAll(Arrays.asList(iO1,iO2));
		ped2.getItems().addAll(Arrays.asList(iO3));
		
		p1.getItems().addAll(Arrays.asList(iO1));
		p2.getItems().addAll(Arrays.asList(iO3));
		p3.getItems().addAll(Arrays.asList(iO2));
		
		itemOrderRepository.saveAll(Arrays.asList(iO1,iO2,iO3));
	}
}
