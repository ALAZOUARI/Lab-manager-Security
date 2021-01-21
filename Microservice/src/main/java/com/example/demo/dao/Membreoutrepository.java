package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Membre_Out_Ids;
import com.example.demo.entities.Membre_Outil;

public interface Membreoutrepository extends JpaRepository<Membre_Outil, Membre_Out_Ids>{
	@Query("select o from Membre_Outil o where developpeur_id=:x")
	List<Membre_Outil> findoutId(@Param("x") Long dev_id); 

}
