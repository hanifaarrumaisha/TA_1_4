package com.apap.tugasakhir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.*;
import com.apap.tugasakhir.service.*;


@RestController
@RequestMapping("/rawat-jalan/poli/jadwal")
public class JadwalPoliController {
	
	@Autowired
	private JadwalPoliService jadwalPoliService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@RequestMapping(value = "/tambah" , method = RequestMethod.GET)
	private String addJadwalPoli (Model model) {
		model.addAttribute("jadwalPoli", new JadwalPoliModel());
		return "addJadwalPoli";
	}
	
	@RequestMapping(value = "/tambah" , method = RequestMethod.POST)
	private String addDealerSubmit(@ModelAttribute JadwalPoliModel jadwalPoli , Model model) {
		jadwalPoliService.addJadwalPoli(jadwalPoli);
		model.addAttribute("title", "Add Successfull");
		return "add";
	}
}
