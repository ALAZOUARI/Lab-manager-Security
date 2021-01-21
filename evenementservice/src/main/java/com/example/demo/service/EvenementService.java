package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EvenementRepository;
import com.example.demo.entities.Evenement;

@Service
public class EvenementService {
	@Autowired
	EvenementRepository evenementRepository ; 
	
	public Evenement addEvenement(Evenement e) {
		return evenementRepository.save(e); 
	}
	public void deleteEvenement(Evenement e) {
		evenementRepository.delete(e);
	}

	public Evenement updateEvenement(Evenement e) {
		return evenementRepository.saveAndFlush(e); 
	}
	
	public Evenement findEvenement(Long id) {
		Evenement e = evenementRepository.findById(id).get();
		return e ;
	}
	
	public void deleteById(Long id) {
		evenementRepository.deleteById(id);
	}
	public List<Evenement> findAll(){
		return evenementRepository.findAll() ; 
	}
	
	public List<Evenement> findByLieu(String lieu){
		return evenementRepository.findByLieu(lieu) ; 
	}
	public List<Evenement> findByLieu(Date date){
		return evenementRepository.findByDate(date) ; 
	}
	

}
