package com.apap.tugasakhir.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugasakhir.model.PoliModel;
import com.apap.tugasakhir.service.PoliService;

@Controller
public class PoliController {

	@Autowired
	private PoliService poliService;
	private int tempId;
	
	@RequestMapping(value="/rawat-jalan/poli/tambah",method = RequestMethod.GET)
	private String addPoli(Model model) {
		model.addAttribute("poli", new PoliModel());
		
		return "add-poli"; //udah ditest
	}
	@RequestMapping(value="/rawat-jalan/poli/tambah", method =RequestMethod.POST)
	private String addPoliSubmit(@ModelAttribute PoliModel poli, Model model) {
		poliService.addPoli(poli);
		model.addAttribute("poli", poli);
		return "add"; //udah di test
	}
	@RequestMapping("/rawat-jalan/poli")
	public String viewPoli(Model model) {
		List<PoliModel> archive = poliService.findAll();
		model.addAttribute("listPoli", archive);
		return "view-poli"; //udah
	}
	@RequestMapping(value = "/rawat-jalan/poli/ubah", method = RequestMethod.GET)
	private String editPoli(@RequestParam("id") Integer id_poli, Model model) {
		PoliModel poli = poliService.getPoliById(id_poli);
		tempId = id_poli;
		model.addAttribute("poli", poli);
		return "ubah-poli"; //udah ditest
	}
	@RequestMapping(value = "/rawat-jalan/poli/ubah", method = RequestMethod.POST)
	private String updatePoliSubmit(@ModelAttribute PoliModel poli, Model model) {
		poli.setId(tempId);
		poliService.updatePoli(poli);
		model.addAttribute("poli", poli);
		return "ubah-poli-success"; //udah ditest
	}
}
