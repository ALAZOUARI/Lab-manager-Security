package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Membre_Evenement;
import com.example.demo.entities.Membre_Evt_Ids;

public interface Membreevtrepository extends JpaRepository<Membre_Evenement, Membre_Evt_Ids>{
	@Query("select e from Membre_Evenement e where organisateur_id=:x")
	List<Membre_Evenement> findevtId(@Param("x") Long org_id); 

}
