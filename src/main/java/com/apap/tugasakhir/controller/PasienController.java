package com.apap.tugasakhir.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.service.RestService;
import com.apap.tugasakhir.service.RujukanService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping("/rawat-jalan/pasien")
public class PasienController {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestService restService;
	
	@Autowired
	RujukanService rujukanService;
	
	RujukanRawatJalanModel rujukanRawatJalanModel;
		
	@RequestMapping("")
	public String getAllPasien(Model model) throws ParseException, JsonParseException, JsonMappingException, IOException {
		rujukanService.getAllPasienRujukan();
		ArrayList<Object> output = (ArrayList<Object>) rujukanService.getAllRujukan();
		HashMap<Integer, RujukanRawatJalanModel> mapRujukan =(HashMap<Integer, RujukanRawatJalanModel>) output.get(0);
		ArrayList<PasienRujukanDetail> allPasien = (ArrayList<PasienRujukanDetail>) output.get(1);
		System.out.println();
		model.addAttribute("allPasien", allPasien);
		model.addAttribute("mapRujukan", mapRujukan);
		return "view-allPasien";
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