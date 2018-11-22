package com.apap.tugasakhir.service;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.PasienDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.PoliRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.rest.StatusPasienDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestServiceImpl implements RestService{
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public String getRest(String url) throws ParseException{
		String response = restTemplate.getForObject(url, String.class);
        return response;
	}
	
	@Override
	public PasienRujukanDetail parsePasien(String data) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(data);
		JSONObject pasienJson = (JSONObject) json.get("result");
		
		System.out.println(pasienJson);
		JSONObject statusJson = (JSONObject) pasienJson.get("statusPasien");
		JSONObject poliJson = (JSONObject) pasienJson.get("poliRujukan");
		
		StatusPasienDetail status = new StatusPasienDetail();
		status.setId((int) (long) statusJson.get("id"));
		status.setJenis((String) statusJson.get("jenis"));
		
		PasienRujukanDetail pasien = new PasienRujukanDetail();
		pasien.setId((int) (long) pasienJson.get("id"));
		pasien.setNama((String) pasienJson.get("nama"));
		String tanggalRujukan = (String) pasienJson.get("tanggalRujukan");
		if (tanggalRujukan != null) {
			pasien.setTanggalRujukan(Date.valueOf(tanggalRujukan));
		}
		pasien.setStatusPasien(status);
		
		if (poliJson != null) {
			PoliRujukanDetail poli = new PoliRujukanDetail();
			poli.setId((int) (long) poliJson.get("id"));
			poli.setNama((String) poliJson.get("nama"));
			pasien.setPoliRujukan(poli);
		}
		
		return pasien;
	}
	
	@Override
	public List<PasienRujukanDetail> parsePasienRujukan(String data) throws ParseException, JsonParseException, JsonMappingException, IOException {
		List<PasienRujukanDetail> allPasien = new ArrayList<PasienRujukanDetail>();
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(data);
		System.out.println("BISA");
		JSONArray res = (JSONArray) json.get("result");
		System.out.println(res);
		
		Iterator i = res.iterator();
		
		System.out.println("characters: "); 
		while (i.hasNext()) { 
			JSONObject pasienJson = (JSONObject) i.next();
			JSONObject statusJson = (JSONObject) pasienJson.get("statusPasien");
			JSONObject poliJson = (JSONObject) pasienJson.get("poliRujukan");
			
			StatusPasienDetail status = new StatusPasienDetail();
			status.setId((int) (long) statusJson.get("id"));
			status.setJenis((String) statusJson.get("jenis"));
			
			PasienRujukanDetail pasien = new PasienRujukanDetail();
			pasien.setId((int) (long) pasienJson.get("id"));
			pasien.setNama((String) pasienJson.get("nama"));
			String tanggalRujukan = (String) pasienJson.get("tanggalRujukan");
			if (tanggalRujukan != null) {
				pasien.setTanggalRujukan(Date.valueOf(tanggalRujukan));
			}
			pasien.setStatusPasien(status);
			
			if (poliJson != null) {
				PoliRujukanDetail poli = new PoliRujukanDetail();
				poli.setId((int) (long) poliJson.get("id"));
				poli.setNama((String) poliJson.get("nama"));
				pasien.setPoliRujukan(poli);
			}
			allPasien.add(pasien);
			System.out.println(pasien.getNama());
		}
		return allPasien;
	}
	
	@Override
	public List<PasienDetail> parseAllPasien(String data) throws ParseException{
		List<PasienDetail> allPasien = new ArrayList<PasienDetail>();
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(data);
		System.out.println("BISA");
		JSONArray res = (JSONArray) json.get("result");
		System.out.println(res);
		
		Iterator i = res.iterator();
		
		System.out.println("characters: "); 
		while (i.hasNext()) { 
			JSONObject pasienJson = (JSONObject) i.next();
			JSONObject statusPasien = (JSONObject) pasienJson.get("statusPasien");
			StatusPasienDetail status = new StatusPasienDetail();
			status.setId((int) (long) statusPasien.get("id"));
			status.setJenis((String) statusPasien.get("jenis"));
			PasienDetail pasien = new PasienDetail();
			pasien.setId((int) (long) pasienJson.get("id"));
			pasien.setNama((String) pasienJson.get("nama"));
			pasien.setStatusPasien(status);
			allPasien.add(pasien);
			System.out.println(pasien.getNama());
		}
		return allPasien;
	}

	@Override
	public DokterDetail getDokter(int idDokter) throws ParseException {
		DokterDetail dokter = new DokterDetail();
		JSONParser parser = new JSONParser();
		String response = restTemplate.getForObject(Setting.siApp+"/getDokter/"+idDokter, String.class);
        System.out.println(response);
        JSONObject json = (JSONObject) parser.parse(response);
        JSONObject result = (JSONObject) json.get("result");
        String nama = (String) result.get("nama");
        long id_dokter = (long) result.get("id");
        dokter.setId((int)id_dokter);
        dokter.setNama(nama);
        System.out.println(nama);
        System.out.println(id_dokter);
        
        return dokter;
	}


}
