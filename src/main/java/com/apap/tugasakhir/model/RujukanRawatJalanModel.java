package com.apap.tugasakhir.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rujukan_rawat_jalan")
public class RujukanRawatJalanModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama_pasien", nullable = false)
	private String nama_pasien;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tanggal_rujuk", nullable = false)
	private Date tanggal_rujuk;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private int status;
	
	@OneToMany(mappedBy = "rujukan_rawat_jalan", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<PenangananModel> listPenanganan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jadwal", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private JadwalPoliModel jadwalPoli;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNama_pasien() {
		return nama_pasien;
	}


	public void setNama_pasien(String nama_pasien) {
		this.nama_pasien = nama_pasien;
	}


	public Date getTanggal_rujuk() {
		return tanggal_rujuk;
	}


	public void setTanggal_rujuk(Date tanggal_rujuk) {
		this.tanggal_rujuk = tanggal_rujuk;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

	public List<PenangananModel> getListPenanganan() {
		return listPenanganan;
	}


	public void setListPenanganan(List<PenangananModel> listPenanganan) {
		this.listPenanganan = listPenanganan;
	}
}
