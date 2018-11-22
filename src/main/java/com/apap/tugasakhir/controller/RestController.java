package com.apap.tugasakhir.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/rawat-jalan")
public class RestController {
	
	@GetMapping(value = "/poli/jadwal/dokter-available")
	private List<DokterModel> dokterAvailable(@RequestBody )
	
}
