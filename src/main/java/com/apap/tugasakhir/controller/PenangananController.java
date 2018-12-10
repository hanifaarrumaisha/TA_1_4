package com.apap.tugasakhir.controller;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.repository.PenangananDb;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.JenisPemeriksaanDetail;
import com.apap.tugasakhir.rest.PemeriksaanDetail;
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
	public String viewAllPenanganan(Model model) throws ParseException {
		List<PenangananModel> listPenanganan = penangananService.getPenangananList();
		
		Map<Integer, String> mapPemeriksaan = penangananService.getDataPemeriksaan();
		
		Timestamp ts=new Timestamp(System.currentTimeMillis());  
        Date date=new Date(ts.getTime());  
        System.out.println(date);  
		
		for(PenangananModel haha : listPenanganan) {
			Date datez = new Date(haha.getWaktu().getTime());
			System.out.println(datez);
		}
		
		model.addAttribute("mapPemeriksaan", mapPemeriksaan);
		model.addAttribute("listPenanganan", listPenanganan);
		
		return "view-riwayat-penanganan";
	}
	

	private PenangananDb penangananDb;
	
	@RequestMapping(value = "/tambah", method = RequestMethod.GET)
	private String pageAddPenanganan(Model model) {
		model.addAttribute("penanganan", new PenangananModel());
		return "add-penanganan";
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	private String addJabatan(@ModelAttribute PenangananModel penanganan, RedirectAttributes redirectAtt){
		penangananService.addPenanganan(penanganan);
		String message = "Penanganan " + " berhasil ditambah";
		redirectAtt.addFlashAttribute("message", message);
		return "redirect:/rawat-jalan/pasien/penanganan/tambah";
	}
	
//	//controller buat coba
//	@RequestMapping(value = "/coba", method = RequestMethod.GET)
//	private String mintaPeriksa(){
//		
//	
//		return penangananService.kirimPenanganan();
//	}
	

}
