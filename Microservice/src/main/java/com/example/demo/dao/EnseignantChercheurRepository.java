package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.entities.EnseignantChercheur;

@Repository
public interface EnseignantChercheurRepository extends JpaRepository<EnseignantChercheur,Long> {
	List<EnseignantChercheur> findByGrade(String grade);

	List<EnseignantChercheur> findByEtablissement(String etablissement);
}