package com.apap.tugasakhir.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.repository.PenangananDb;
import com.apap.tugasakhir.rest.JenisPemeriksaanDetail;


@Service
@Transactional
public class PenangananServiceImpl implements PenangananService {
	@Autowired
	private PenangananDb penangananDb;
	private RestServiceImpl restService;
	
	@Override
	public void addPenanganan(PenangananModel penanganan) {
		// TODO Auto-generated method stub
		penangananDb.save(penanganan);
	}

	@Override
	public List<PenangananModel> getPenangananList() {
		// TODO Auto-generated method stub
		return penangananDb.findAll();
	}
	
	@Override
	public Map<Integer, String> getDataPemeriksaan(){
		Map<Integer, String> mapPemeriksaan = new HashMap<Integer,String>();
		mapPemeriksaan.put(1, "Salivary cyst marsupial"); 
		mapPemeriksaan.put(2, "Inject stereoid");
		mapPemeriksaan.put(3, "Open testicular biopsy");
		mapPemeriksaan.put(4, "Hand skin graft NEC");
		mapPemeriksaan.put(5, "Transplant hand tend NEC");
		mapPemeriksaan.put(6, "Wedg resec entropion rep");
		mapPemeriksaan.put(7, "Percutaneous hysterogram");
		mapPemeriksaan.put(8, "Control bladd hemorrhage");
		mapPemeriksaan.put(9, "Local gastr destruct NEC");
		mapPemeriksaan.put(10, "Thorac exc lung lesion");
		mapPemeriksaan.put(11, "cek darah");
		return mapPemeriksaan;
		
	}
}
