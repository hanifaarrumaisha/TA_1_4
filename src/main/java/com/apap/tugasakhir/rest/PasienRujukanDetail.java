package com.apap.tugasakhir.rest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PasienRujukanDetail {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("nama")
	private String nama;
	
	@JsonProperty("tanggalRujukan")
	private Date tanggalRujukan;
	
	@JsonProperty("statusPasien")
	private StatusPasienDetail statusPasien;
	
	@JsonProperty("poliRujukan")
	private PoliRujukanDetail poliRujukan;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public Date getTanggalRujukan() {
		return tanggalRujukan;
	}
	public void setTanggalRujukan(Date tanggalRujukan) {
		this.tanggalRujukan = tanggalRujukan;
	}
	public StatusPasienDetail getStatusPasien() {
		return statusPasien;
	}
	public void setStatusPasien(StatusPasienDetail statusPasien) {
		this.statusPasien = statusPasien;
	}
	public PoliRujukanDetail getPoliRujukan() {
		return poliRujukan;
	}
	public void setPoliRujukan(PoliRujukanDetail poliRujukan) {
		this.poliRujukan = poliRujukan;
	}
}
