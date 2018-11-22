package com.apap.tugasakhir.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusPasienDetail {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("jenis")
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