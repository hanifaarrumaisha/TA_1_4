package com.apap.tugasakhir.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.*;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.service.*;


@Controller
@RequestMapping("/rawat-jalan/poli/jadwal")
public class JadwalPoliController {
	
	@Autowired
	private JadwalPoliService jadwalPoliService;
	
	
	@RequestMapping(value = "/tambah" , method = RequestMethod.GET)
	private String addJadwalPoli (Model model) {
		model.addAttribute("jadwalPoli", new JadwalPoliModel());
		return "addJadwalPoli";
	}
	
	@RequestMapping(value = "/tambah" , method = RequestMethod.POST)
	private String addJadwalSubmit(@ModelAttribute JadwalPoliModel jadwalPoli , Model model) {
		jadwalPoliService.addJadwalPoli(jadwalPoli);
		model.addAttribute("title", "Add Successfull");
		return "add";
	}
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	private String viewJadwalPoli(Model model) throws ParseException {
		List<JadwalPoliModel> listJadwalPoli = jadwalPoliService.getAllJadwalPoli();
		Map<Integer, DokterDetail> mapDokter = jadwalPoliService.getDoctor();
		Map<Integer, HariPoliModel> mapHari = new HashMap<Integer, HariPoliModel>();
		for (JadwalPoliModel jadwalPoli : listJadwalPoli) {
			for (HariPoliModel hariPoli : jadwalPoli.getPoli().getListHariPoli()){
				mapHari.put(jadwalPoli.getPoli().getId(), hariPoli);
			}
		}
		model.addAttribute("mapHari", mapHari);
		model.addAttribute("listJadwalPoli", listJadwalPoli);
		model.addAttribute("mapDokter", mapDokter);
		return "view-jadwal-poli";
	}
}
