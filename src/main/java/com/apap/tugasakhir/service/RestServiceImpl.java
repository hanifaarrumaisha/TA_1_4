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
import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.model.PoliModel;
import com.apap.tugasakhir.repository.PoliDb;
import com.apap.tugasakhir.rest.BaseResponse;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.PasienIGDDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.PoliRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.apap.tugasakhir.rest.StatusPasienDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class RestServiceImpl implements RestService{
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PoliDb poliDb;
	
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
//		TODO get status pasien detail\
		
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
	public List<PasienRujukanDetail> parseListPasien(String data) throws ParseException, JsonParseException, JsonMappingException, IOException {
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
	public List<PasienRujukanDetail> parseAllPasien(String data) throws ParseException{
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
			JSONObject statusPasien = (JSONObject) pasienJson.get("statusPasien");
			StatusPasienDetail status = new StatusPasienDetail();
			status.setId((int) (long) statusPasien.get("id"));
			status.setJenis((String) statusPasien.get("jenis"));
			PasienRujukanDetail pasien = new PasienRujukanDetail();
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
	
	@Override
	public List<DokterDetail> getAllDokter() throws ParseException {
		List<DokterDetail> listDokter = new ArrayList<DokterDetail>();
		
		String response = restTemplate.getForObject(Setting.siApp+"/4/getAllDokter/", String.class);
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response);
		System.out.println("BISA");
		JSONArray res = (JSONArray) json.get("result");
		System.out.println(res);
		
		Iterator i = res.iterator();
		
		System.out.println("characters: "); 
		while (i.hasNext()) { 
			DokterDetail dokter = new DokterDetail();
			JSONObject dokterJson = (JSONObject) i.next();
	        System.out.println(response);
	        String nama = (String) dokterJson.get("nama");
	        long id_dokter = (long) dokterJson.get("id");
	        dokter.setId((int)id_dokter);
	        dokter.setNama(nama);
	        System.out.println(nama);
	        System.out.println(id_dokter);
	        listDokter.add(dokter);
		}
        return listDokter;
	}
	
	@Override
	public void updateStatusPasien(PasienRujukanDetail pasien) {
		BaseResponse response = restTemplate.postForObject(Setting.siApp+"/4/updatePasien", pasien, BaseResponse.class);
		System.out.println(response.getResult());
		
	}

	@Override
	public ArrayList<PasienRujukanDetail> parsePasienIGD(String response) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(response);
		System.out.println("BISA");
		JSONArray res = (JSONArray) json.get("result");
		System.out.println(res);
		
		ArrayList<PasienRujukanDetail> allPasien = new ArrayList<PasienRujukanDetail>();
		
		Iterator i = res.iterator();
		
		System.out.println("characters: "); 
		while (i.hasNext()) { 
			JSONObject pasienJson = (JSONObject) i.next();
			JSONObject statusJson = (JSONObject) pasienJson.get("status");
			
			StatusPasienDetail status = new StatusPasienDetail((int) (long) statusJson.get("id"), (String) statusJson.get("jenis"));
			
			System.out.println(pasienJson);
			PasienIGDDetail pasien = new PasienIGDDetail( 
					(int) (long) pasienJson.get("id"), 
					(int) (long) pasienJson.get("idPasien"), 
					(int) (long) pasienJson.get("idDokter"), 
					Date.valueOf((String) pasienJson.get("waktuMasuk")), 
					Date.valueOf((String) pasienJson.get("waktuUpdate")), 
					(String) pasienJson.get("keterangan"), 
					(PenangananModel) pasienJson.get("detailPenanganan"), 
					(int) (long) pasienJson.get("idPoli"), 
					status);
			
			PasienRujukanDetail pasienRujukan = parseIGDtoGeneral(pasien);
	        allPasien.add(pasienRujukan);
		}
		return allPasien;
	}
	
	@Override
	public PasienRujukanDetail parseIGDtoGeneral(PasienIGDDetail pasienIgd) throws ParseException{
		String response = getRest(Setting.siApp+"/getPasien/"+pasienIgd.getIdPasien());
		PasienRujukanDetail pasienSiApp = parsePasien(response);
		String nama = pasienSiApp.getNama();
//		TODO ADA KEMUNGKINAN POLI YANG DITUJU GA ADA
		PoliModel poli = poliDb.getOne(pasienIgd.getIdPoli());
		PoliRujukanDetail poliDetail = new PoliRujukanDetail(poli.getId(), poli.getNama());
		PasienRujukanDetail pasien = new PasienRujukanDetail(pasienIgd.getIdPasien(), nama, pasienIgd.getWaktuMasuk(), pasienIgd.getStatus(), poliDetail);
		return pasien;
	}
}
