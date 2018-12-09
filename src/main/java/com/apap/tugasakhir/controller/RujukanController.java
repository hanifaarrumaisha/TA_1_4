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
import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.repository.RujukanRawatJalanDb;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.rest.StatusPasienDetail;
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
	public String changeStatus(@RequestParam(value = "idRujukan") Long idRujukan, Model model) throws ParseException {
		RujukanRawatJalanModel pasien = rujukanDb.getOne(idRujukan);
		model.addAttribute("pasien",pasien);
		model.addAttribute("statusLama", pasien.getStatus());
		return "change-statusPasien";
	}

	@RequestMapping(value="/rawat-jalan/pasien/ubah", method=RequestMethod.POST)
	private String changeStatusSubmit(@RequestParam("statusLama") int statusLama, @ModelAttribute RujukanRawatJalanModel pasienRujukan, RedirectAttributes redirectAtt) throws ParseException{
		System.out.println("status baru " + pasienRujukan.getStatus());
		System.out.println("status lama " + statusLama);
		rujukanService.changeRujukan(pasienRujukan, statusLama);
		String message = "";
		if(statusLama == pasienRujukan.getStatus()) {
			message = "Status Pasien " + pasienRujukan.getNamaPasien() + " gagal diubah";
		}
		else {
			message = "Status Pasien " + pasienRujukan.getNamaPasien() + " berhasil diubah!";
		}
		redirectAtt.addFlashAttribute("message", message);
		return "redirect:/rawat-jalan/pasien";
	}
	
	/**
	@RequestMapping(value = "/rawat-jalan/pasien/penanganan/test", method = RequestMethod.GET)
	private String tambahObatLab(@RequestParam(value = "idRujukan") Long idRujukan, Model model) {
		model.addAttribute("penanganan", new PenangananModel());
		model.addAttribute("idRujukan", idRujukan);
		//System.out.println(jenis)
		return "add-ObatLab";
	}
	

	@RequestMapping(value = "/rawat-jalan/pasien/penanganan/test", method = RequestMethod.POST)
	private String tambahObatLabSubmit(@RequestParam("jenis") String jenis,@ModelAttribute PenangananModel penanganan, RedirectAttributes redirectAtt) {
		//System.out.println(jenis);
		System.out.println("jenis");
		return "add-ObatLab";
	}
	**/
}
