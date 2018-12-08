package com.apap.tugasakhir.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.service.RestService;
import com.apap.tugasakhir.service.RujukanService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/rawat-jalan/pasien")
public class PasienController {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestService restService;
	
	@Autowired
	RujukanService rujukanService;
	
	RujukanRawatJalanModel rujukanRawatJalanModel;
		
	@RequestMapping("/getAllPasien")
	public List<PasienRujukanDetail> getAllPasien() throws ParseException, JsonParseException, JsonMappingException, IOException {
		// get from SiAppointment
		String urlApp = Setting.siApp+"/4/getAllPasienRawatJalan/";
		String responseApp = restService.getRest(urlApp);
		ArrayList<PasienRujukanDetail> listPasien = (ArrayList<PasienRujukanDetail>) restService.parsePasienRujukan(responseApp);
		for (PasienRujukanDetail pasien : listPasien) {
			System.out.println("masuk");
			rujukanService.validateRujukan(pasien);
		}
		
		// get from SiIGD
		String urlIGD = Setting.siIGD+"/rujukan";
		String responseIGD = restService.getRest(urlIGD);
		// TODO buat parse data pasien dari IGD di RestService
		// TODO ubah ke pasienRujukanDetail
		return listPasien;
	}
	
	
	
	@RequestMapping("/getPasien")
	public PasienRujukanDetail getPasien() throws ParseException {
		String url = Setting.siApp+"/getPasien/1";
		String response = restService.getRest(url);
		return restService.parsePasien(response);
	}

	@RequestMapping("/getAllDokter")
	public List<DokterDetail> getAllDOkter() throws ParseException{
		return restService.getAllDokter();
	}
}