package com.example.demo;

import java.util.Date;

import lombok.Data;
@Data
public class PublicationBean {
	private Long id;
	private String titre;
	private String type;  //article de journal/ manifestation/chapitre de livre/livre/poster
	private Date dateApparition;
	private String lien;
	private String sourcePdf;

}
