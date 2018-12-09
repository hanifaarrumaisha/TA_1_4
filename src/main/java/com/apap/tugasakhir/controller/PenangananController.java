package com.apap.tugasakhir.controller;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugasakhir.model.ObatModel;
import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.repository.PenangananDb;
import com.apap.tugasakhir.service.ObatService;
import com.apap.tugasakhir.service.PenangananService;
import com.apap.tugasakhir.service.RestService;
import org.json.simple.parser.ParseException;


@Controller
@RequestMapping("/rawat-jalan/pasien/penanganan")
public class PenangananController {

	@Autowired
	private PenangananService penangananService;
	
	@Autowired
	private ObatService obatService;
	
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
	
	@RequestMapping(value = "/tambah/", method = RequestMethod.GET)
	private String pageAddPenanganan(@RequestParam(value ="idRujukan") Long idRujukan, @RequestParam("jenis") Optional<String> jenis, Model model) {
		model.addAttribute("penanganan", new PenangananModel());
		if(jenis.isPresent()) {
			model.addAttribute("jenis", jenis.get());
		}
		else {
			model.addAttribute("jenis", jenis);
		}
		List<ObatModel> listObat = obatService.getListObat();
		
		return "add-ObatLab";
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	private String addJabatan(@RequestParam(value ="jenis") String jenis, @ModelAttribute PenangananModel penanganan, RedirectAttributes redirectAtt){
		penangananService.addPenanganan(penanganan);
		System.out.println("jenis"+ jenis);
		return "SubmitJenisSucces";
		/**
		penangananService.addPenanganan(penanganan);
		String message = "Penanganan " + " berhasil ditambah";
		redirectAtt.addFlashAttribute("message", message);
		return "redirect:/rawat-jalan/pasien/penanganan/tambah";
		**/
	}
	
	@RequestMapping(value = "/coba", method = RequestMethod.GET)
	private String mintaPeriksa(){
		
	
		return penangananService.kirimPenanganan();
	}
	

}
