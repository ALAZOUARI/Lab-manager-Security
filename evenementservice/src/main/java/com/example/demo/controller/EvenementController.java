package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Evenement;
import com.example.demo.service.EvenementService;

@RestController
public class EvenementController {
	@Autowired
	EvenementService evenementService;

	@GetMapping(value = "/evenements")
	public List<Evenement> findAllevenements() {
		return evenementService.findAll();
	}

	@GetMapping(value = "/evenements/{id}")
	public Evenement findoneMembre(@PathVariable Long id) {
		return evenementService.findEvenement(id);
	}
	@GetMapping("/evenements/lieu")
	public List<Evenement> findEventLieu(@RequestParam String lieu){
		return evenementService.findByLieu(lieu); 
	}
	
	@PostMapping("/evenements")
	public Evenement addEvenement(@RequestBody Evenement evt) {
		 return evenementService.addEvenement(evt); 
	}
	@PutMapping("/evenements/{id}")
	public Evenement updateEvenement(@PathVariable Long id , @RequestBody Evenement evt) {
		evt.setId(id);
		return evenementService.updateEvenement(evt); 
		
	}
	@DeleteMapping(value="/evenements/{id}")
	public void deleteevent(@PathVariable Long id) {
		evenementService.deleteById(id);
	}

	


}
