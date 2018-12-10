package com.apap.tugasakhir.controller;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugasakhir.model.ObatModel;
import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.repository.PenangananDb;
import com.apap.tugasakhir.service.ObatService;
import com.apap.tugasakhir.service.PenangananService;
import com.apap.tugasakhir.service.RestService;
import com.apap.tugasakhir.service.RujukanService;


@Controller
@RequestMapping("/rawat-jalan/pasien/penanganan")
public class PenangananController {

	@Autowired
	private PenangananService penangananService;
	
	@Autowired
	private ObatService obatService;
	
	@Autowired
	private RestService restService;
	
	@Autowired
	private RujukanService rujukanService;
			
	@GetMapping()
	public String viewAllPenanganan(Model model) throws ParseException {
		List<PenangananModel> listPenanganan = penangananService.getPenangananList();
		Map<Integer, String> mapPemeriksaan = penangananService.getDataPemeriksaan();
		
		Timestamp ts=new Timestamp(System.currentTimeMillis());  
        Date date=new Date(ts.getTime());  
        System.out.println(date);  
		
		for(PenangananModel haha : listPenanganan) {
//			Date datez = new Date(haha.getWaktu().getTime());
//			System.out.println(datez);
		}
		
		model.addAttribute("mapPemeriksaan", mapPemeriksaan);
		model.addAttribute("listPenanganan", listPenanganan);
		
		return "view-riwayat-penanganan";
	}
	

	private PenangananDb penangananDb;
	
	@RequestMapping(value = "/tambah", method = RequestMethod.GET)
	private String pageAddPenanganan(@RequestParam(value ="idRujukan") Long idRujukan, @RequestParam("jenis") Optional<Integer> jenis, Model model) throws ParseException {
		if(jenis.isPresent()) {
			//model.addAttribute("penanganan", penanganan);
			model.addAttribute("idRujukan", idRujukan);
			model.addAttribute("jenis", jenis.get());
			PenangananModel penanganan= new PenangananModel();
			RujukanRawatJalanModel rujukan = rujukanService.getRujukanById(idRujukan);
			rujukan.setId(idRujukan);
			penanganan.setRujukanRawatJalan(rujukan);
			model.addAttribute("penanganan", penanganan);
			Map<Integer, String> mapPemeriksaan1 = penangananService.getDataPemeriksaan();
			System.out.println(mapPemeriksaan1.size());
			System.out.print("ini jenis: "+jenis);
			if(jenis.get() == 1) {
				penanganan.setJenisPemeriksaan(null);
				List<ObatModel> listObat = obatService.getListObat();
				model.addAttribute("listObat", listObat);
				System.out.println("size obat: "+listObat.size());
			}
			else if(jenis.get() == 2) {
				penanganan.setObat(null);
				Map<Integer, String> mapPemeriksaan = penangananService.getDataPemeriksaan();
				model.addAttribute("mapPemeriksaan", mapPemeriksaan);
			}
		}
		else {
			model.addAttribute("idRujukan", idRujukan);
			model.addAttribute("jenis", jenis);
		}
		return "add-ObatLab";
	}
	
	@RequestMapping(value = "/tambah", method = RequestMethod.POST)
	private String addPenanganan(@RequestParam(value ="idRujukan") Long idRujukan,
			@RequestParam("jenis") Optional<Integer> jenis,
			@ModelAttribute PenangananModel penanganan, Model model) throws ParseException{
		model.addAttribute("idRujukan", idRujukan);
		RujukanRawatJalanModel rujukan = rujukanService.getRujukanById(idRujukan);
		penanganan.setRujukanRawatJalan(rujukan);
		System.out.println("jenis lab: " + penanganan.getJenisPemeriksaan());			
		if(jenis.get() == 1) {
			System.out.println("masuk obat");
			penangananService.addPenanganan(penanganan);
			
		}
		else if(jenis.get() == 2){
			System.out.println(penanganan.getDeskripsi());
			
			penangananService.addPenanganan(penanganan);
			
		}
		System.out.println("jenis"+ jenis.get());		
		return "SubmitJenisSuccess";
		/*
		penangananService.addPenanganan(penanganan);
		String message = "Penanganan " + " berhasil ditambah";
		redirectAtt.addFlashAttribute("message", message);
		return "redirect:/rawat-jalan/pasien/penanganan/tambah";
		**/
	}
	
//	//controller buat coba
//	@RequestMapping(value = "/coba", method = RequestMethod.GET)
//	private String mintaPeriksa(){
//		
//	
//		return penangananService.kirimPenanganan();
//	}
	

}
