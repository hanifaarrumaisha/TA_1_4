package com.apap.tugasakhir.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasakhir.model.PenangananModel;
import java.util.List;
import com.apap.tugasakhir.model.RujukanRawatJalanModel;

@Repository
public interface PenangananDb extends JpaRepository<PenangananModel, Integer>{
	List<PenangananModel> findByRujukanRawatJalan(RujukanRawatJalanModel model);
}