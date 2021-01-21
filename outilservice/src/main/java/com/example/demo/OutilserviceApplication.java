package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Outil;
import com.example.demo.service.OutilService;

@SpringBootApplication
@EnableDiscoveryClient
public class OutilserviceApplication implements CommandLineRunner {
	@Autowired
	OutilService outilService; 
	
	@Autowired
	RepositoryRestConfiguration config ; 

	public static void main(String[] args) {
		SpringApplication.run(OutilserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		config.exposeIdsFor(Outil.class); 
		Outil outil = new Outil(new Date(),"source");
		outilService.addOutil(outil); 
		
	}

}
