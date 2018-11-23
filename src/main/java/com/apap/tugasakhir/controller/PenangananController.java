package com.apap.tugasakhir.controller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.service.PenangananService;
import com.apap.tugasakhir.service.RestService;

@Controller
@RequestMapping("/rawat-jalan/pasien/penanganan")
public class PenangananController {

	@Autowired
	private PenangananService penangananService;
	
	@Autowired
	private RestService restService;
	
	@GetMapping()
	public String viewAllPenanganan(Model model) {
		List<PenangananModel> listPenanganan = penangananService.getPenangananList();
	
		model.addAttribute("listPenanganan", listPenanganan);
		
		return "view-riwayat-penanganan";
	}
	
	@GetMapping(value = "/asd")
	public String viewallDokter(Model model) throws ParseException {
		List<DokterDetail> asd = restService.getAllDokter();
		
		System.out.println(asd.size());
		model.addAttribute("asd",asd);
		return "asd";
	}
}
