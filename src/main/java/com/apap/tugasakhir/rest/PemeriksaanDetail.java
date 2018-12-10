package com.apap.tugasakhir.rest;

import java.sql.Date;

public class PemeriksaanDetail {
	private Date Waktu;
	
	private Integer jenis;
	
	private Integer pasien;

	public Date getWaktu() {
		return Waktu;
	}

	public void setWaktu(Date waktu) {
		Waktu = waktu;
	}

	public Integer getJenis() {
		return jenis;
	}

	public void setJenis(Integer jenis) {
		this.jenis = jenis;
	}

	public Integer getPasien() {
		return pasien;
	}

	public void setPasien(Integer pasien) {
		this.pasien = pasien;
	}
	
	
}
