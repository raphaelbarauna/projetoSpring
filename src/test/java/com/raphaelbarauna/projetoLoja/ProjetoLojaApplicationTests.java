package com.raphaelbarauna.projetoLoja;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjetoLojaApplicationTests {

	@Test
	public void contextLoads() {

		try{
			int dividend = 69;
			int divisor = 0;
			System.out.println(dividend/divisor);
		} catch(ArithmeticException e){
			System.out.println("Bug");
		}
		finally{
			System.out.println("Bag");
		}
		System.out.println("Big");
	}


}
