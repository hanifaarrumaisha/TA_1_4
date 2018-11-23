package com.apap.tugasakhir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.repository.PenangananDb;
import com.apap.tugasakhir.service.PenangananService;

@Controller
@RequestMapping("/rawat-jalan/pasien/penanganan")
public class PenangananController {
	@Autowired
	private PenangananService penangananService;
	
	@Autowired
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

}
