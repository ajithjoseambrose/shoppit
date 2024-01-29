package com.orderservice.shoppit;

import com.orderservice.shoppit.service.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ShoppitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppitApplication.class, args);
		System.out.println("Testing vSix");
	}

}
