package com.apap.tugasakhir.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.repository.JadwalPoliDb;

public class JadwalPoliServiceImpl implements JadwalPoliService	 {
	@Autowired
	private JadwalPoliDb jadwalPoliDb;
	
	@Override
	public void addJadwalPoli(JadwalPoliModel jadwalPoli) {
		jadwalPoliDb.save(jadwalPoli);
	}

}