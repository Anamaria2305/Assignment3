package com.example.demo2;

import com.example.demo2.entity.Admins;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

@SpringBootApplication
public class Demo2Application  {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);


	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
			registry.addMapping("/**").allowedMethods("GET","POST","PUT","DELETE").allowedOrigins("*").allowedHeaders("*");
			}
		};
	}
}
