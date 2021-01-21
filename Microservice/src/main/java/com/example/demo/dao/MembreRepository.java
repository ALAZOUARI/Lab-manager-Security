package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Membre;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
	boolean existsByCin(String cin);

	Optional<Membre> findByCin(String cin);

	List<Membre> findByNom(String caractere);

	Membre findByEmail(String email);

	boolean existsByEmail(String email);

}