package com.apap.tugasakhir.rest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RujukanLabDetail {
	
	@JsonProperty("pasien")
	private Integer pasien;
	
	@JsonProperty("waktu")
	private Date waktu;
	
	@JsonProperty("jenis")
	private Integer jenis;

	public Integer getPasien() {
		return pasien;
	}

	public void setPasien(Integer pasien) {
		this.pasien = pasien;
	}

	public Date getWaktu() {
		return waktu;
	}

	public void setWaktu(Date waktu) {
		this.waktu = waktu;
	}

	public Integer getJenis() {
		return jenis;
	}

	public void setJenis(Integer jenis) {
		this.jenis = jenis;
	}
	
	
	
}
