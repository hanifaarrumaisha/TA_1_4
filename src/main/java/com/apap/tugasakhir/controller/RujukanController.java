package com.apap.tugasakhir.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.repository.RujukanRawatJalanDb;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.service.RestService;
import com.apap.tugasakhir.service.RujukanService;

@Controller
public class RujukanController {
	@Autowired
	RestTemplate restTemplate;
	

	@Autowired
	RestService restService;
	
	@Autowired
	private RujukanService rujukanService;
	
	@Autowired
	private RujukanRawatJalanDb rujukanDb;
	
	
	@RequestMapping(value= "/rawat-jalan/pasien/{idRujukan}", method = RequestMethod.GET)
	private String detilPasien(@PathVariable(value="idRujukan") Long idRujukan, Model model) throws ParseException {
		RujukanRawatJalanModel rujukan = rujukanService.getRujukanById(idRujukan);
		String url = Setting.siApp+"/getPasien/"+rujukan.getIdPasien();
		String response = restService.getRest(url);
		PasienRujukanDetail pasien = restService.parsePasien(response);
		//int idPasien = pasien.getId()
		model.addAttribute("rujukan",rujukan);
		model.addAttribute("status",pasien.getStatusPasien().getJenis());
		return "view-pasien";
	}
	
	@RequestMapping(value="/rawat-jalan/pasien/ubah", method=RequestMethod.GET)
	public String changeStatus(@RequestParam(value = "idPasien") Long idPasien, Model model) throws ParseException {
//		RujukanRawatJalanModel rujukan = rujukanService.getRujukanById(idRujukan);
		
		String url = Setting.siApp+"/getPasien/"+idPasien;
		String response = restService.getRest(url);
		PasienRujukanDetail pasien = restService.parsePasien(response);
		model.addAttribute("pasien",pasien);
		
//		model.addAttribute("rujukan",rujukan);
		return "change-statusPasien";
	}
	

	@RequestMapping(value="/rawat-jalan/pasien/ubah", method=RequestMethod.POST)
	private String changeStatusSubmit(@ModelAttribute PasienRujukanDetail pasien, RedirectAttributes redirectAtt) throws ParseException{
//		System.out.println("nama" + String.valueOf(rujukan.getIdPasien()));
//		String url = Setting.siApp+"/getPasien/"+rujukan.getIdPasien();
//		String response = restService.getRest(url);
//		System.out.println(response);
//		PasienRujukanDetail pasien = restService.p	arsePasien(response);
		System.out.println(pasien.getId());
		int statusLama = pasien.getStatusPasien().getId();
		System.out.println(statusLama);
		String statusPasien = pasien.getStatusPasien().getJenis();
		rujukanService.changeRujukan(pasien, statusPasien);
		String message = "";
		if(statusLama == pasien.getStatusPasien().getId()) {
			message = "Status Pasien " + pasien.getNama() + " gagal diubah";
		}
		else {
			message = "Status Pasien " + pasien.getNama() + " berhasil diubah!";
		}
		redirectAtt.addFlashAttribute("message", message);
		return "redirect:/rawat-jalan/pasien/ubah";
	}

}
