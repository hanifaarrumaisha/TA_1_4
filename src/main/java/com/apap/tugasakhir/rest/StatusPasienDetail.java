package com.apap.tugasakhir.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value={"jenis"}, allowSetters=true)
public class StatusPasienDetail {
	public StatusPasienDetail() {
		super();
	}

	public StatusPasienDetail(int id, String jenis) {
		super();
		this.id = id;
		this.jenis = jenis;
	}

	@JsonProperty("id")
	private int id;
	
	//@JsonIgnore
	@JsonProperty("jenis")
	@JsonIgnore
	private String jenis;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//@JsonIgnore
	public String getJenis() {
		return jenis;
	}
	
	//@JsonIgnore
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
}
