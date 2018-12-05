package com.apap.tugasakhir.model;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "penanganan")
public class PenangananModel implements Serializable {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column (name = "waktu", nullable = false)
	private Timestamp waktu;
	
	@JsonIgnore
	@NotNull
	@Size(max = 255)
	@Column (name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@Size(max = 255)
	@Column(name = "jenis_pemeriksaan", nullable = true)
	private Integer jenisPemeriksaan;
	

	public Integer getJenisPemeriksaan() {
		return jenisPemeriksaan;
	}

	public void setJenisPemeriksaan(Integer jenisPemeriksaan) {
		this.jenisPemeriksaan = jenisPemeriksaan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "obat", referencedColumnName = "id", nullable = true)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ObatModel obat;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rujukan_rawat_jalan", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private RujukanRawatJalanModel rujukanRawatJalan;
	
	public Timestamp getWaktu() {
		return waktu;
	}

	public void setWaktu(Timestamp waktu) {
		this.waktu = waktu;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public ObatModel getObat() {
		return obat;
	}

	public void setObat(ObatModel obat) {
		this.obat = obat;
	}

	public RujukanRawatJalanModel getRujukanRawatJalan() {
		return rujukanRawatJalan;
	}

	public void setRujukanRawatJalan(RujukanRawatJalanModel rujukanRawatJalan) {
		this.rujukanRawatJalan = rujukanRawatJalan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}