package com.example.demo.entities;

import java.util.Date;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@DiscriminatorValue("etd")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant extends Membre {
	@Temporal(TemporalType.DATE)
	private Date dateInscription ; 
	private String diplome ;
	@ManyToOne
	private EnseignantChercheur encadrant ; 

	@Builder
	public Etudiant(String cin, String nom, String prenom, Date date, byte[] photo, String cv, String email,
			String password , Date DateInscription,String Diplome,EnseignantChercheur enseignantChercheur) {
		super(cin, nom, prenom, date, cv, email, password);
		// TODO Auto-generated constructor stub
		this.dateInscription=DateInscription ; 
		this.diplome=Diplome ;  
		this.encadrant=enseignantChercheur; 


}
}