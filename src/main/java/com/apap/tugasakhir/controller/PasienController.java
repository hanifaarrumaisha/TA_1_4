package com.apap.tugasakhir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.rest.Setting;

@RestController
@RequestMapping("/rawat-jalan/pasien")
public class PasienController {
	@Autowired
	RestTemplate restTemplate;
	
//	@Bean
//	public RestTemplate rest() {
//		return new RestTemplate();
//	}
	
	@RequestMapping()
	public String getPasien() {
        String response = restTemplate.getForObject(Setting.siApp+"/getAllPasienRawatJalan/", String.class);
        System.out.println(response);
        return response;
	}
	
//	@Autowired
//	private PasienService pasienService;
	
	
}