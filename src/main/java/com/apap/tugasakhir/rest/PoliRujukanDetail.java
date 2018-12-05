package com.apap.tugasakhir.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value={"nama"}, allowSetters= true)
public class PoliRujukanDetail {
	@JsonProperty("id")
	private int id;
	
	//@JsonIgnore
	@JsonProperty("nama")
	private String nama;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//@JsonIgnore
	public String getNama() {
		return nama;
	}
	
	//@JsonIgnore
	public void setNama(String nama) {
		this.nama = nama;
	}
}
