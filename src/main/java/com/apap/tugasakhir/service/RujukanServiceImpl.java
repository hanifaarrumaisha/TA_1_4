package com.apap.tugasakhir.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.repository.JadwalPoliDb;
import com.apap.tugasakhir.repository.PoliDb;
import com.apap.tugasakhir.repository.RujukanRawatJalanDb;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.rest.StatusPasienDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
@Transactional

public class RujukanServiceImpl implements RujukanService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestService restService;

	@Autowired
	private RujukanRawatJalanDb rujukanDb;

	@Autowired
	private JadwalPoliDb jadwalPoliDb;
	
	@Autowired
	private PoliDb poliDb;

	@Override
	public RujukanRawatJalanModel getRujukanById(Long id) {
		// TODO Auto-generated method stub
		return rujukanDb.getOne(id);
	}

	@Override
	public void changeRujukan(RujukanRawatJalanModel pasienRujuk, int statusLama) throws ParseException {
		if (pasienRujuk.getStatus() > statusLama && pasienRujuk.getStatus() - statusLama == 1) {
			rujukanDb.save(pasienRujuk);
			System.out.println("masuk update status berhasil");
			System.out.println(pasienRujuk.getStatus());
		}else {
			System.out.println("gagal, ga valid");
			pasienRujuk.setStatus(statusLama);
		}
		if (pasienRujuk.getStatus() == 3) {
			System.out.println("post status");
			String response = restService.getRest(Setting.siApp+"/getPasien/"+pasienRujuk.getIdPasien());
			System.out.println(response);
			System.out.println("expected 3: "+pasienRujuk.getStatus());
			PasienRujukanDetail pasien = restService.parsePasien(response);
			pasien.setStatusPasien(new StatusPasienDetail(9, "Selesai di Rawat Jalan"));
			restService.updateStatusPasien(pasien);
		}
	}

	@Override
	public void validateRujukan(PasienRujukanDetail pasien) {
		if (!rujukanDb.findByIdPasien(pasien.getId()).isPresent()) {
			// get jadwal terdekat
			
			List<JadwalPoliModel> results = jadwalPoliDb.findByPoliIdAndTanggalGreaterThanEqualOrderByTanggalDesc(pasien.getPoliRujukan().getId(), pasien.getTanggalRujukan());
			if (results.size() > 0 && pasien.getPoliRujukan()!=null) {
				RujukanRawatJalanModel rujukan = new RujukanRawatJalanModel();
				rujukan.setIdPasien(pasien.getId());
				rujukan.setJadwalPoli(results.get(0));
				rujukan.setNamaPasien(pasien.getNama());
				rujukan.setStatus(1);
				rujukan.setListPenanganan(new ArrayList<PenangananModel>());
				rujukan.setTanggalRujuk(pasien.getTanggalRujukan());
				rujukanDb.save(rujukan);
			}
		}
	}
	
	@Override
	public List<PasienRujukanDetail> getAllPasienRujukan() throws ParseException, JsonParseException, JsonMappingException, IOException {
		// get from SiAppointment
				String urlApp = Setting.siApp+"/4/getAllPasienRawatJalan/";
				String responseApp = restService.getRest(urlApp);
				ArrayList<PasienRujukanDetail> listPasien = (ArrayList<PasienRujukanDetail>) restService.parseListPasien(responseApp);
				
				// get from SiIGD
				String urlIGD = Setting.siIGD+"/rujukan";
				String responseIGD = restService.getRest(urlIGD);
				ArrayList<PasienRujukanDetail> listPasienIGD = (ArrayList<PasienRujukanDetail>) restService.parsePasienIGD(responseIGD);
				listPasien.addAll(listPasienIGD);
				for (PasienRujukanDetail pasien : listPasien) {
					validateRujukan(pasien);
				}
		return listPasien;
	}
	
	@Override
	public List<RujukanRawatJalanModel> getAllRujukan() throws ParseException, JsonParseException, JsonMappingException, IOException{
		List<RujukanRawatJalanModel> allRujukan = rujukanDb.findAll();
		System.out.println("expected 3: "+ rujukanDb.getOne((long) 4).getStatus());
		return allRujukan;
	}

}
