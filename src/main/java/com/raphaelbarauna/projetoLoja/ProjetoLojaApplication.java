package com.raphaelbarauna.projetoLoja;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raphaelbarauna.projetoLoja.domain.Category;
import com.raphaelbarauna.projetoLoja.domain.Product;
import com.raphaelbarauna.projetoLoja.repositories.CategoryRepository;
import com.raphaelbarauna.projetoLoja.repositories.ProductRepository;

@SpringBootApplication
public class ProjetoLojaApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

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
	}

}
