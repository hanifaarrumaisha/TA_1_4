package com.apap.tugasakhir.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apap.tugasakhir.model.PoliModel;

@Repository
public interface PoliDb  extends JpaRepository<PoliModel, Integer>{
}
