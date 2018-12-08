package com.apap.tugasakhir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.tugasakhir.model.HariPoliModel;

@Repository
public interface HariPoliDb extends JpaRepository<HariPoliModel, Integer> {

}
