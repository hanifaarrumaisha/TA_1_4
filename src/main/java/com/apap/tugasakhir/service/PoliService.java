package com.apap.tugasakhir.service;
import java.util.List;

import com.apap.tugasakhir.model.PoliModel;

public interface PoliService {
	void addPoli(PoliModel poli);
	List<PoliModel> findAll();
	PoliModel updatePoli(PoliModel poli);
	PoliModel getPoliById(Integer id);
}
