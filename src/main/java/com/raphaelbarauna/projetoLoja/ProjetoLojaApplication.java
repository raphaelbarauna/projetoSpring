package com.raphaelbarauna.projetoLoja;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.raphaelbarauna.projetoLoja.domain.*;
import com.raphaelbarauna.projetoLoja.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.raphaelbarauna.projetoLoja.domain.enums.StatusPayment;
import com.raphaelbarauna.projetoLoja.domain.enums.TypeCustomer;


@SpringBootApplication
public class ProjetoLojaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoLojaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}