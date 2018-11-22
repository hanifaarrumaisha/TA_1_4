package com.apap.tugasakhir.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasakhir.model.PenangananModel;

public interface PenangananDb extends JpaRepository<PenangananModel, Integer>{

}
