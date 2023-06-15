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
	}
	@Autowired
	private SimpleEmail simpleEmail;
//	@EventListener(ApplicationEvent.class)
//	public void simpleEmail(){
//		simpleEmail.sendEmail("ajithj71@gmail.com","Test my own email", "Hi, Ajith this is just to test the email..");
//	}

}
