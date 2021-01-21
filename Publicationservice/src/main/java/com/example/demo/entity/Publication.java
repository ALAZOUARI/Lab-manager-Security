package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Publication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ; 
	@NonNull
	private String titre ;
	@NonNull
	private String type ; 
	@Temporal(TemporalType.DATE)
	@NonNull
	private Date dateApparition ; 
	@NonNull
	private String lien ;
	@NonNull
	private String sourcePdf; 
	
}
