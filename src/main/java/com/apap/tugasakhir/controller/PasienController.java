package com.apap.tugasakhir.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.PasienDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.service.RestService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/rawat-jalan/pasien")
public class PasienController {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestService restService;
		
	@RequestMapping("/getAllPasien")
	public List<PasienRujukanDetail> getAllPasien() throws ParseException, JsonParseException, JsonMappingException, IOException {
		String url = Setting.siApp+"/4/getAllPasienRawatJalan/";
		String response = restService.getRest(url);
		return restService.parsePasienRujukan(response);
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