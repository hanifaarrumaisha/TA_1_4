package com.apap.tugasakhir.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface RujukanService {
	RujukanRawatJalanModel getRujukanById(Long id);
	void changeRujukan(PasienRujukanDetail pasien, String status) throws ParseException;
	void validateRujukan(PasienRujukanDetail pasien);
	ArrayList<Object> getAllRujukan() throws ParseException, JsonParseException, JsonMappingException, IOException;
	List<PasienRujukanDetail> getAllPasienRujukan()
			throws ParseException, JsonParseException, JsonMappingException, IOException;

}
