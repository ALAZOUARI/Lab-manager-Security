package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Publication;
@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
	Publication findByTitre(String titre);
	
	
	

}
