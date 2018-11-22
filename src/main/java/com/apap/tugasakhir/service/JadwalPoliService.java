package com.apap.tugasakhir.service;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.rest.*;

public interface JadwalPoliService {
	void addJadwalPoli(JadwalPoliModel jadwalPoli);
	List<JadwalPoliModel> getAllJadwalPoli();
	Map<Integer, DokterDetail> getDoctor() throws ParseException;
}	
