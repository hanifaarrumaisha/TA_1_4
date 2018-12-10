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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.apap.tugasakhir.model.*;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.service.*;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/rawat-jalan/poli/jadwal")
public class JadwalPoliController {
	
	@Autowired
	private JadwalPoliService jadwalPoliService;
	
	@Autowired
	private RestService restService;
	
	
	@RequestMapping(value = "/tambah" , method = RequestMethod.GET)
	private String addJadwalPoli (Model model) throws ParseException {
		List<JadwalPoliModel> listJadwalPoli = jadwalPoliService.getAllJadwalPoli();
		Map<Integer, DokterDetail> mapDokter = jadwalPoliService.getDoctor();
		List<DokterDetail> listDokter = restService.getAllDokter();
		model.addAttribute("listDokter", listDokter);
		model.addAttribute("listJadwalPoli", listJadwalPoli);
		model.addAttribute("mapDokter", mapDokter);
		model.addAttribute("jadwalPoli", new JadwalPoliModel());
		return "add-jadwal-poli";
	}
	
	@RequestMapping(value = "/tambah" , method = RequestMethod.POST)
	private String addJadwalSubmit(@ModelAttribute JadwalPoliModel jadwalPoli , Model model) {
		jadwalPoliService.addJadwalPoli(jadwalPoli);
		model.addAttribute("title", "Add Successfull");
		return "add-success";
	}
	
	@RequestMapping(value = "" , method = RequestMethod.GET)
	private String viewJadwalPoli(Model model) throws ParseException {
		List<JadwalPoliModel> listJadwalPoli = jadwalPoliService.getAllJadwalPoli();
		Map<Integer, DokterDetail> mapDokter = jadwalPoliService.getDoctor();
		Map<Integer, HariPoliModel> mapHari = new HashMap<Integer, HariPoliModel>();
		System.out.println(mapDokter.keySet());
		for (JadwalPoliModel jadwalPoli : listJadwalPoli) {
			for (HariPoliModel hariPoli : jadwalPoli.getPoli().getListHariPoli()){
				mapHari.put(jadwalPoli.getPoli().getId(), hariPoli);
			}
		}
		
		
//		System.out.println("Ini nama poli" + listJadwalPoli.get(0).getPoli().getNama());
//		System.out.println("Ini nama dokter" + mapDokter.get(0).getNama());
		model.addAttribute("mapHari", mapHari);
		model.addAttribute("listJadwalPoli", listJadwalPoli);
		model.addAttribute("mapDokter", mapDokter);
		return "view-jadwal-poli";
	}
	
	@RequestMapping(value = "/ubah/{idJadwalPoli}" , method = RequestMethod.GET)
	private String ubahJadwalPoli (@PathVariable (value = "idJadwalPoli") int idJadwalPoli , Model model) throws ParseException {
		List<JadwalPoliModel> listJadwalPoli = jadwalPoliService.getAllJadwalPoli();
		JadwalPoliModel jadwalPoli = jadwalPoliService.getById(idJadwalPoli);
		Map<Integer, DokterDetail> mapDokter = jadwalPoliService.getDoctor();
		List<DokterDetail> listDokter = restService.getAllDokter();
		model.addAttribute("listDokter", listDokter);
		model.addAttribute("jadwalPoli", jadwalPoli);
		model.addAttribute("listJadwalPoli", listJadwalPoli);
		model.addAttribute("mapDokter", mapDokter);
		return "update-jadwal-poli";
	}
	
	@RequestMapping(value = "/ubah/{idJadwalPoli}" , method = RequestMethod.POST)
	private String ubahJadwalPoli (@PathVariable (value = "idJadwalPoli") int idJadwalPoli , JadwalPoliModel jadwalPoli, Model model) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNow = sdf.parse(java.time.LocalDate.now().toString());
        Date dateJadwalPoli = sdf.parse(jadwalPoli.getTanggal().toString());
		
        if (dateNow.before(dateJadwalPoli)) {
        	jadwalPoliService.updateJadwalPoli(jadwalPoli, idJadwalPoli);
    		model.addAttribute("title", "update success");
    		return "update-jadwal-poli-success";
        }else {
        	model.addAttribute("title", "Gagal update");
    		return "update-jadwal-poli-success";
        }
		
		
	}
}
