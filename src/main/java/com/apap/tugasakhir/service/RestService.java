package com.apap.tugasakhir.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.PasienDetail;
import com.apap.tugasakhir.rest.PasienIGDDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RestService {

	List<PasienRujukanDetail> parseAllPasien(String data) throws ParseException;

	String getRest(String url) throws ParseException;

	PasienRujukanDetail parsePasien(String data) throws ParseException;
	
	DokterDetail getDokter(int idDokter) throws ParseException;

	List<DokterDetail> getAllDokter() throws ParseException;

	ArrayList<PasienRujukanDetail> parsePasienIGD(String responseApp) throws ParseException;

	List<PasienRujukanDetail> parseListPasien(String data)
			throws ParseException, JsonParseException, JsonMappingException, IOException;

	PasienRujukanDetail parseIGDtoGeneral(PasienIGDDetail pasienIgd) throws ParseException;
	
	void updateStatusPasien(PasienRujukanDetail pasien);
	
}
