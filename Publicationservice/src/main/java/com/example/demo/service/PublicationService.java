package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PublicationRepository;
import com.example.demo.entity.Publication;


@Service
public class PublicationService {
	@Autowired
	PublicationRepository publicationRepository ; 
	
	public Publication addPublication(Publication o) {
		publicationRepository.save(o);
		return o;
	}
	public Publication findPublication(Long id) {
		return publicationRepository.findById(id).get();
	}
	
	public Publication findPublicationByTitre(String titre) {
		return publicationRepository.findByTitre(titre);
	}
	public void deletePublication(Publication o) {
		publicationRepository.delete(o);
	}

	public Publication updatePublication(Publication o) {
		return publicationRepository.saveAndFlush(o); 
	}
	
	public void deleteById(Long id) {
		publicationRepository.deleteById(id);
	}
	public List<Publication> findAll(){
		return publicationRepository.findAll() ; 
	}
	

}
