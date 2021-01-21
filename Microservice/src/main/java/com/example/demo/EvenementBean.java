package com.example.demo;

import java.util.Date;

import lombok.Data;
@Data
public class EvenementBean {
	private Long id;
	private String title;
	private Date date;
	private String lieu;
}
