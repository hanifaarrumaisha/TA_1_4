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
		ArrayList<RujukanRawatJalanModel> allRujukan = (ArrayList<RujukanRawatJalanModel>) rujukanService.getAllRujukan();
		System.out.println("ukuran "+allRujukan.size());
		System.out.println(allRujukan.get(0).getJadwalPoli().getPoli().getNama());
		System.out.println(allRujukan.get(1).getJadwalPoli().getPoli().getNama());
		System.out.println(allRujukan.get(2).getJadwalPoli().getPoli().getNama());
		System.out.println(allRujukan.get(3).getJadwalPoli().getPoli().getNama());
		System.out.println(allRujukan.get(4).getJadwalPoli().getTanggal());
		model.addAttribute("allRujukan", allRujukan);
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