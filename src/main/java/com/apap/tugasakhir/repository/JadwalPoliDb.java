package com.apap.tugasakhir.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.rest.DokterDetail;

public interface JadwalPoliDb extends JpaRepository<JadwalPoliModel, Integer> {
	List<DokterDetail> findByIdAndTanggal(int id, Date tanggal);


}
