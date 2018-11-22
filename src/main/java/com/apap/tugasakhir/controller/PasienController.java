package com.apap.tugasakhir.controller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.rest.PasienDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.service.RestService;

@RestController
@RequestMapping("/rawat-jalan/pasien")
public class PasienController {
	@Autowired
	RestTemplate restTemplate;
	

	@Autowired
	RestService restService;
	
		
	@RequestMapping("/getAllPasien")
	public List<PasienDetail> getAllPasien() throws ParseException {
		String url = Setting.siApp+"/getAllPasienRawatJalan/";
		String response = restService.getRest(url);
		return restService.parseAllPasien(response);
	}
	

	@RequestMapping("/getPasien")
	public PasienDetail getPasien() throws ParseException {
		String url = Setting.siApp+"/getPasien/1";
		String response = restService.getRest(url);
		return restService.parsePasien(response);
	}
}