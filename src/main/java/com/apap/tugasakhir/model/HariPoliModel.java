package com.apap.tugasakhir.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Hari_Poli")
public class HariPoliModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_poli", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private PoliModel poli;
	
	@NotNull
	@Size(max=255)
	@Column(name = "hari", nullable = false)
	private String hari;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PoliModel getPoli() {
		return poli;
	}

	public void setPoli(PoliModel poli) {
		this.poli = poli;
	}

	public String getHari() {
		return hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}
	
	
	
	
}
