package com.apap.tugasakhir.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.repository.JadwalPoliDb;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.controller.*;

@Service
@Transactional
public class JadwalPoliServiceImpl implements JadwalPoliService	 {
	@Autowired
	private JadwalPoliDb jadwalPoliDb;
	
	@Autowired
	private RestService restService;
	
	@Override
	public void addJadwalPoli(JadwalPoliModel jadwalPoli) {
		jadwalPoliDb.save(jadwalPoli);
	}

	@Override
	public List<JadwalPoliModel> getAllJadwalPoli() {
		// TODO Auto-generated method stub
		return jadwalPoliDb.findAll();
	}
	
	

	@Override
	public Map<Integer, DokterDetail> getDoctor() throws ParseException {
		Map<Integer, DokterDetail> mapDokter = new HashMap<Integer, DokterDetail>();
		List<JadwalPoliModel> listJadwalPoli = jadwalPoliDb.findAll();
		for (JadwalPoliModel jadwalPoli : listJadwalPoli){
			DokterDetail dokter = restService.getDokter(jadwalPoli.getIdDokter());
			mapDokter.put(jadwalPoli.getPoli().getId(), dokter);
		}
		return mapDokter;
	}
	
//	public List<DokterDetail> viewDokterAvailble
	
}
