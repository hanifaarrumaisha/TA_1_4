package com.apap.tugasakhir.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	
	@JsonProperty("jenis")
	@JsonIgnore
	private String jenis;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
}
