package com.apap.tugasakhir.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tugasakhir.model.ObatModel;
import com.apap.tugasakhir.rest.BaseResponse;
import com.apap.tugasakhir.service.ObatService;

@RestController
@RequestMapping("/rawat-jalan")
public class ApiController {
	@Autowired
	ObatService obatService;
	
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
}
