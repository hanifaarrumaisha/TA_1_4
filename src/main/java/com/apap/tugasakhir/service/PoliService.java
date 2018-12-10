package com.apap.tugasakhir.service;
import java.util.ArrayList;
import java.util.List;

import com.apap.tugasakhir.model.PoliModel;

public interface PoliService {
	void addPoli(PoliModel poli);
	List<PoliModel> findAll();
	PoliModel updatePoli(PoliModel poli);
	PoliModel getPoliById(Integer id);
	void createHariPoli(String[] array, PoliModel poli);
	ArrayList<String> turnIntoString(PoliModel poli);
	void resetHariPoli(PoliModel poli);
}
