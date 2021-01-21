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
public class Membre_Out_Ids implements Serializable{
	private Long outil_id ; 
	private Long developer_id ; 

}
