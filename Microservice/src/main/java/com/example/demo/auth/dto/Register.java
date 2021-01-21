package com.example.demo.auth.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.demo.entities.Membre;
import com.example.demo.entities.Role;

import lombok.NonNull;

public class Register {
	private Long id ; 
	private String cin;

	private String nom;

	private String prenom;

	private Date date;


	private String cv;

	private String email;

	private String password;

	private Set<String> role;

	public Register() {
	}

	public Register(String cin, String nom, String prenom, Date date, String cv, String email,
			String password, Set<String> role) {
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		
		this.cv = cv;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

}
