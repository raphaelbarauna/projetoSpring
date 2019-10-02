package com.raphaelbarauna.projetoLoja;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class ProjetoLojaApplication implements CommandLineRunner {
	
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
	

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLojaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		

		Category cat1 = new Category(null,"informatica");
		Category cat2 = new Category(null,"escritorio");
		
		Product p1 = new Product(null, "computador", 2000.00);
		Product p2 = new Product(null, "impressora", 800.00);
		Product p3 = new Product(null, "mouse", 80.00);
			
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));	
				
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
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
