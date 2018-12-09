package com.apap.tugasakhir.rest;

import java.sql.Date;

import com.apap.tugasakhir.model.PenangananModel;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PasienIGDDetail {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("idPasien")
	private int idPasien;
	
	@JsonProperty("idDokter")
	private int idDokter;
	
	@JsonProperty("waktuMasuk")
	private Date waktuMasuk;
	
	@JsonProperty("waktuUpdate")
	private Date waktuUpdate;
	
	public PasienIGDDetail(int id, int idPasien, int idDokter, Date waktuMasuk, Date waktuUpdate, String keterangan,
			PenangananModel detailPenanganan, int idPoli, StatusPasienDetail status) {
		super();
		this.id = id;
		this.idPasien = idPasien;
		this.idDokter = idDokter;
		this.waktuMasuk = waktuMasuk;
		this.waktuUpdate = waktuUpdate;
		this.keterangan = keterangan;
		this.detailPenanganan = detailPenanganan;
		this.idPoli = idPoli;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(int idPasien) {
		this.idPasien = idPasien;
	}

	public int getIdDokter() {
		return idDokter;
	}

	public void setIdDokter(int idDokter) {
		this.idDokter = idDokter;
	}

	public Date getWaktuMasuk() {
		return waktuMasuk;
	}

	public void setWaktuMasuk(Date waktuMasuk) {
		this.waktuMasuk = waktuMasuk;
	}

	public Date getWaktuUpdate() {
		return waktuUpdate;
	}

	public void setWaktuUpdate(Date waktuUpdate) {
		this.waktuUpdate = waktuUpdate;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public PenangananModel getDetailPenanganan() {
		return detailPenanganan;
	}

	public void setDetailPenanganan(PenangananModel detailPenanganan) {
		this.detailPenanganan = detailPenanganan;
	}

	public int getIdPoli() {
		return idPoli;
	}

	public void setIdPoli(int idPoli) {
		this.idPoli = idPoli;
	}

	public StatusPasienDetail getStatus() {
		return status;
	}

	public void setStatus(StatusPasienDetail status) {
		this.status = status;
	}

	@JsonProperty("keterangan")
	private String keterangan;
	
	@JsonProperty("detailPenanganan")
	private PenangananModel detailPenanganan;
	
	@JsonProperty("idPoli")
	private int idPoli;
	
	@JsonProperty("status")
	private StatusPasienDetail status;
}
