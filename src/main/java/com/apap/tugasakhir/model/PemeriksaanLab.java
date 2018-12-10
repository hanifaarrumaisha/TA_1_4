package com.apap.tugasakhir.model;

public class PemeriksaanLab {
	private Integer id;
	private String jenis;
	
	
	public PemeriksaanLab(Integer id, String jenis) {
		super();
		this.id = id;
		this.jenis = jenis;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getJenis() {
		return jenis;
	}


	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	
}
