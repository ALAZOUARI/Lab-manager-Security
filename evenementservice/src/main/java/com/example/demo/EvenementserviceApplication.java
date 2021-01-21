package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Evenement;
import com.example.demo.service.EvenementService;

@SpringBootApplication
@EnableDiscoveryClient

public class EvenementserviceApplication implements CommandLineRunner{
	
	@Autowired
	EvenementService evenementService; 
	
	@Autowired
	RepositoryRestConfiguration repositoryRestConfiguration ;

	public static void main(String[] args) {
		SpringApplication.run(EvenementserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Evenement.class); 
		Evenement evenement = new Evenement("evt",new Date(),"Sfax"); 
		evenementService.addEvenement(evenement); 
		
		
	}

}
