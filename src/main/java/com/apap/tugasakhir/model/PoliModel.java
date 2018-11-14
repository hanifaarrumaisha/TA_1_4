package com.apap.tugasakhir.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "poli")
public class PoliModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(max = 255)
	@Column (name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column (name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@OneToMany(mappedBy = "jadwal_poli", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<JadwalPoliModel> listJadwalPoli;
	
	@OneToMany(mappedBy = "hari_poli", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<HariPoliModel> listHariPoli;
}
