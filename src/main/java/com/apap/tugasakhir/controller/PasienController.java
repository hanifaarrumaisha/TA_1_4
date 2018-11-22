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
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@RequestMapping()
	public String getPasien() {
        String response = restTemplate.getForObject(Setting.siApp+"/getAllPasienRawatJalan/", String.class);
        System.out.println(response);
        return response;
	}
	
//	@Autowired
//	private PasienService pasienService;
	
	
	@RequestMapping("/getAllPasien")
	private Object getAllPasien() throws Exception{
		String path = Setting.siApp+ "/getAllPasienIGD";
		String detailPasien = restTemplate.getForObject(path, String.class);

		return detailPasien;

//		HttpHeaders headers = new HttpHeaders(); 
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.add("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Mobile Safari/537.36");
//		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//		String path = Setting.apiUrl+ "/getAllPasienIGD";
//		return restTemplate.exchange(path, HttpMethod.GET, entity, Object.class);
		
//		List<PasienModel> pasien = pasienService.getAllPasien();
//		PasienDetail detailPasien = restTemplate.getForObject(path, PasienDetail.class);
//		return detailPasien;
	}

}