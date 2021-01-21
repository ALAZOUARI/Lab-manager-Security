package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OutilRepository;
import com.example.demo.entities.Outil;

@Service
public class OutilService {

	@Autowired
	OutilRepository outilRepository;

	public Outil addOutil(Outil o) {
		outilRepository.save(o);
		return o;
	}
	public void deleteOutil(Outil o) {
		outilRepository.delete(o);
	}

	public Outil updateOutil(Outil o) {
		return outilRepository.saveAndFlush(o); 
	}
	public Outil findOutil(Long id) {
		return outilRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		outilRepository.deleteById(id);
	}
	public List<Outil> findAll(){
		return outilRepository.findAll() ; 
	}
	
	public Outil findBySource(String source){
		return outilRepository.findBySource(source) ; 
	}
	
}
