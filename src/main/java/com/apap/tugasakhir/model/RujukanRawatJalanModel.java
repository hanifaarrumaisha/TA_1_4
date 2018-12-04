package com.apap.tugasakhir.model;

import java.sql.Date;
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
	@Column(name = "id_pasien", nullable = false)
	private int idPasien;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama_pasien", nullable = false)
	private String namaPasien;
	
	@NotNull
	@Column(name = "tanggal_rujuk", nullable = false)
	private Date tanggalRujuk;
	
	@NotNull
	@Column(name = "status", nullable = false)
	private int status;
	
	@OneToMany(mappedBy = "rujukanRawatJalan", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<PenangananModel> listPenanganan;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_jadwal", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private JadwalPoliModel jadwalPoli;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIdPasien() {
		return idPasien;
	}

	public void setIdPasien(int idPasien) {
		this.idPasien = idPasien;
	}

	public String getNamaPasien() {
		return namaPasien;
	}

	public void setNamaPasien(String namaPasien) {
		this.namaPasien = namaPasien;
	}

	public Date getTanggalRujuk() {
		return tanggalRujuk;
	}

	public void setTanggalRujuk(Date tanggalRujuk) {
		this.tanggalRujuk = tanggalRujuk;
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

	public JadwalPoliModel getJadwalPoli() {
		return jadwalPoli;
	}

	public void setJadwalPoli(JadwalPoliModel jadwalPoli) {
		this.jadwalPoli = jadwalPoli;
	}
	
	
}
