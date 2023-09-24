package com.royal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Clase01Application {

	public static void main(String[] args) {
		SpringApplication.run(Clase01Application.class, args);
		System.out.println("**** SPRING BOOT INICIADO *******");
	}

}
