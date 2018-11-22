package com.apap.tugasakhir.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasienDetail {
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String nama;
	
	private StatusPasienDetail statusPasien;

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public StatusPasienDetail getStatusPasien() {
		return statusPasien;
	}

	public void setStatusPasien(StatusPasienDetail statusPasien) {
		this.statusPasien = statusPasien;
	}
	
}
