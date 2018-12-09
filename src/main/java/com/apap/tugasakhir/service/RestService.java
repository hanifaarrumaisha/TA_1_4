package com.apap.tugasakhir.service;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.rest.JenisPemeriksaanDetail;
import com.apap.tugasakhir.rest.PasienDetail;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RestService {

	List<PasienDetail> parseAllPasien(String data) throws ParseException;

	String getRest(String url) throws ParseException;

	PasienRujukanDetail parsePasien(String data) throws ParseException;

	List<PasienRujukanDetail> parsePasienRujukan(String data) throws ParseException, JsonParseException, JsonMappingException, IOException;
	
	DokterDetail getDokter(int idDokter) throws ParseException;

	List<DokterDetail> getAllDokter() throws ParseException;
	
	void updateStatusPasien(PasienRujukanDetail pasien);

	List<JenisPemeriksaanDetail> getAllJenisPemeriksaan() throws ParseException;

	
}
