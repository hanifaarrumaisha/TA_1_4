	package com.apap.tugasakhir.service;

import javax.transaction.Transactional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.repository.RujukanDb;
import com.apap.tugasakhir.rest.PasienRujukanDetail;



@Service
@Transactional

public class RujukanServiceImpl implements RujukanService{
	@Autowired
	RestTemplate restTemplate;
	

	@Autowired
	RestService restService;
	
	@Autowired
	private RujukanDb rujukanDb;
	
	@Override
	public RujukanRawatJalanModel getRujukanById(Long id) {
		// TODO Auto-generated method stub
		return rujukanDb.getOne(id);
	}
	
	@Override
	public void changeRujukan(PasienRujukanDetail pasien,String status) throws ParseException {
		int idStatusNow = 0;
//		RujukanRawatJalanModel rujukanChange = rujukanDb.getOne(id);
		if(status.equalsIgnoreCase("Mendaftar di Rawat Jalan")) idStatusNow = 7;
		else if(status.equalsIgnoreCase("Berada di Rawat Jalan")) idStatusNow = 8;
		else if(status.equalsIgnoreCase("Selesai di Rawat Jalan")) idStatusNow = 9;
		if(pasien.getStatusPasien().getId()< idStatusNow && idStatusNow-pasien.getStatusPasien().getId() == 1) {
			pasien.getStatusPasien().setId(idStatusNow);
			pasien.getStatusPasien().setJenis(status);
		}
	}
	
}
