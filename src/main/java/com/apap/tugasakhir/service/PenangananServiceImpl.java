package com.apap.tugasakhir.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.repository.PenangananDb;
import com.apap.tugasakhir.rest.JenisPemeriksaanDetail;
import com.apap.tugasakhir.rest.PemeriksaanDetail;
import com.apap.tugasakhir.rest.Setting;


@Service
@Transactional
public class PenangananServiceImpl implements PenangananService {
	@Autowired
	private PenangananDb penangananDb;
	private RestServiceImpl restService;
	
	@Autowired
	RestTemplate restTemplate;
	
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
	
//	@Override
//	public String kirimPenanganan() {
//		
//		
//		PemeriksaanDetail pemeriksaans = new PemeriksaanDetail();
//	
//		
//		pemeriksaans.setJenis(1);
//		pemeriksaans.setPasien(1);
//		
//		long time = System.currentTimeMillis();
//		Date date = new Date(time);
//		pemeriksaans.setWaktu(date);
//		
//		String pemeriksaan = restTemplate.postForObject(Setting.siLab+ "/lab/pemeriksaan/permintaan", pemeriksaans, String.class);
//		return pemeriksaan;
//	}

	@Override
	public String kirimPenanganan() {
		// TODO Auto-generated method stub
		//long time = System.currentTimeMillis();
		
		PemeriksaanDetail wew = new PemeriksaanDetail();
		List<PenangananModel> asd = this.getPenangananList();
		
		for(PenangananModel qwe : asd) {
			if(qwe.getJenisPemeriksaan() != null) {
				wew.setJenis(qwe.getJenisPemeriksaan());
				wew.setPasien(qwe.getRujukanRawatJalan().getIdPasien());
				Date date = new Date(qwe.getWaktu().getTime());
				wew.setWaktu(date);
			}
		}
		String pemeriksaan = restTemplate.postForObject(Setting.siLab+ "/lab/pemeriksaan/permintaan", wew, String.class);
		return pemeriksaan;
	}
}
