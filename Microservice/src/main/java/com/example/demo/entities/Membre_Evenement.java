package com.example.demo.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre_Evenement {
	@EmbeddedId
	private Membre_Evt_Ids id;
	@ManyToOne
	@MapsId("organisateur_id")
    private Membre organisateur;	

}
