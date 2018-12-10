package com.apap.tugasakhir.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugasakhir.model.HariPoliModel;
import com.apap.tugasakhir.model.PoliModel;
import com.apap.tugasakhir.service.PoliService;

@Controller
public class PoliController {

	@Autowired
	private PoliService poliService;
	
	private int tempId;
	
	@RequestMapping(value="/rawat-jalan/poli/tambah",method = RequestMethod.GET)
	private String addPoli(Model model) {
		String[] kumpHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
		ArrayList<String> daftarHari = new ArrayList<>(Arrays.asList(kumpHari));
		
		
		model.addAttribute("poli", new PoliModel());
		
		return "add-poli"; //udah ditest
	}
	@RequestMapping(value="/rawat-jalan/poli/tambah", method =RequestMethod.POST)
	private String addPoliSubmit(@ModelAttribute PoliModel poli, Model model) {
		poliService.addPoli(poli);
		String[] kumpHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
		ArrayList<String> daftarHari = new ArrayList<>(Arrays.asList(kumpHari));
		//buat opsi
		
		model.addAttribute("poli", poli);
		System.out.println(poli.getId());
		tempId=poli.getId();
		//buat ngambil id nya poli buat jd paramnya createHariPoli
		
		ArrayList<String> listHariTerpilih = new ArrayList<>();
		
		//HariPoliModel hariPoli = new HariPoliModel();
		//ArrayList<HariPoliModel> listHariPoli = new ArrayList<>();
		//listHariPoli.add(hariPoli);
		//poli.setListHariPoli(listHariPoli);
		//System.out.println(poli.getListHariPoli().size());
		
		//model.addAttribute("listHariPoli", listHariPoli);
		model.addAttribute("daftarHari", daftarHari);
		//model.addAttribute("hariPoli", hariPoli);
		model.addAttribute(listHariTerpilih);
		return "hari-poli"; 
	}
	//nanti bikin yang /rawat-jalan/poli/tambah-hari method post
	@RequestMapping(value="/rawat-jalan/poli/tambah-hari", method = RequestMethod.POST)
	public String tambahHari(@RequestParam("listHariTerpilih") String[] listHariTerpilih, Model model) {
		poliService.createHariPoli(listHariTerpilih, poliService.getPoliById(tempId));
		return "poli-success";
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
		String[] kumpHari = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
		ArrayList<String> daftarHari = new ArrayList<>(Arrays.asList(kumpHari));
		//buat opsi
		
		//mengubah ArrayList<HariPoliModel> jadi <String> menjadi String[]
		ArrayList<String> tempArray = poliService.turnIntoString(poli);
		String[] array = tempArray.toArray(new String[tempArray.size()]);
		
		model.addAttribute("tempArray", array);
		model.addAttribute("daftarHari", daftarHari);
		model.addAttribute("poli", poli);
		return "ubah-poli"; //udah ditest
	}
	@RequestMapping(value = "/rawat-jalan/poli/ubah", method = RequestMethod.POST)
	private String updatePoliSubmit(@ModelAttribute PoliModel poli, @RequestParam("listHariTerpilih") String[] listHariTerpilih, Model model) {
		poliService.resetHariPoli(poliService.getPoliById(tempId));
		poli.setId(tempId);
		//System.out.println("HOy" + poli.getListHariPoli());
		
		poliService.createHariPoli(listHariTerpilih, poliService.getPoliById(tempId)); 
		//lah terus kalo harinya di unchecked dia keapus di db?
		//kalo dr checked ke un kke checked jadi nambah di b=db tp id nya beda
		//jadinya ngereplace array yg dia punya (ngedd baru), tp remove jg janlup
		
		poliService.updatePoli(poli);
		model.addAttribute("poli", poli);
		return "ubah-poli-success"; //udah ditest
	}
}
