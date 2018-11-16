package com.apap.tugasakhir.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "penanganan")
public class PenangananModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column (name = "waktu", nullable = false)
	private Date waktu;
	
	@NotNull
	@Size(max = 255)
	@Column (name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "obat", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private ObatModel obat;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getWaktu() {
		return waktu;
	}

	public void setWaktu(Date waktu) {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rujukan_rawat_jalan", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private RujukanRawatJalanModel rujukanRawatJalan;
}
