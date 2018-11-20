package com.apap.tugasakhir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tugasakhir.model.PoliModel;
import com.apap.tugasakhir.service.PoliService;

@Controller
public class PoliController {

	@Autowired
	private PoliService poliService;
	
	@RequestMapping(value="/rawat-jalan/poli/tambah",method = RequestMethod.GET)
	private String addPoli(Model model) {
		model.addAttribute("poli", new PoliModel());
		
		return "add-poli"; //belum dibuat
	}
	@RequestMapping(value="/rawat-jalan/poli/tambah", method =RequestMethod.POST)
	private String addPoliSubmit(@ModelAttribute PoliModel poli, Model model) {
		poliService.addPoli(poli);
		model.addAttribute("poli", poli);
		return "add"; //belum dibuat
	}
	@RequestMapping("/rawat-jalan/poli")
	public String viewPoli(Model model) {
		List<PoliModel> archive = poliService.findAll();
		model.addAttribute("listPoli", archive);
		return "view-poli"; //belum dibuat
	}
}
