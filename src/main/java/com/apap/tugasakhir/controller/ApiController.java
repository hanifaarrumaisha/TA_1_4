package com.apap.tugasakhir.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugasakhir.model.ObatModel;
import com.apap.tugasakhir.repository.JadwalPoliDb;
import com.apap.tugasakhir.rest.BaseResponse;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.service.ObatService;
import com.apap.tugasakhir.service.RestService;

@RestController
@RequestMapping("/rawat-jalan")
public class ApiController {
	@Autowired
	ObatService obatService;
	
	@Autowired
	RestService restService;
	
	@Autowired
	JadwalPoliDb jadwalPoliDb;
	
	@PostMapping(value = "/obat/tambah")
	public BaseResponse<ObatModel> addObat(@RequestBody @Valid ObatModel obat, BindingResult bindingResult){
		BaseResponse<ObatModel> response = new BaseResponse<ObatModel>();
		if (bindingResult.hasErrors() || (obat.getJumlah() < 0)) {
			response.setStatus(500);
			response.setMessage("error data");
		} else {
			Optional<ObatModel> optObat = obatService.findByNama(obat.getNama());
			if (optObat.isPresent()) {
				ObatModel selectedObat = optObat.get();
				selectedObat.setJumlah(selectedObat.getJumlah()+obat.getJumlah());
				obatService.save(selectedObat);
			}
			else {
				obatService.save(obat);
			}
			response.setStatus(200);
            response.setMessage("success");
            response.setResult(obatService.findByNama(obat.getNama()).get());
		}
		return response;
	}
	
	 @GetMapping(value = "/poli/jadwal/dokter-available")
	    public BaseResponse<List<DokterDetail>> getAllDokter(@RequestParam Date tanggal) throws ParseException {
	        List<DokterDetail> dokterAvailable = new ArrayList<DokterDetail>();
		 	BaseResponse<List<DokterDetail>> response = new BaseResponse<List<DokterDetail>>();
	        List<DokterDetail> allDokter = restService.getAllDokter();
	        if (allDokter.size() < 1) {
	            response.setStatus(500);
	            response.setMessage("error data");
	        } else {
	        	for(DokterDetail dokter : allDokter) {
	        		if(jadwalPoliDb.findByIdDokterAndTanggal(dokter.getId(), tanggal).size() == 0) {
	        			System.out.println(dokter.getId());
	        			dokterAvailable.add(dokter);
	        		}
	    	          
	        	}
	        	response.setStatus(200);
  	            response.setMessage("success");
  	            response.setResult(dokterAvailable);
	        }
	        return response;
	    }
	
}
