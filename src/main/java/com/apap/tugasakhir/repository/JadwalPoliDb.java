package com.apap.tugasakhir.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.model.PoliModel;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;

@Repository
public interface JadwalPoliDb extends JpaRepository<JadwalPoliModel, Integer> {
	List<DokterDetail> findByIdAndTanggal(int id, Date tanggal);
	List<JadwalPoliModel> findByPoliIdAndTanggalGreaterThanEqualOrderByTanggalDesc(int idPoli, Date tanggal);
	JadwalPoliModel findById(int id);
}
