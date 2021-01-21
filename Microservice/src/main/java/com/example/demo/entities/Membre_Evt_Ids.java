package com.example.demo.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre_Evt_Ids implements Serializable{
	private Long evenement_id ; 
	private Long organisateur_id; 

}
