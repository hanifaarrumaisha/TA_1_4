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
	void validateRujukan(PasienRujukanDetail pasien);
	List<RujukanRawatJalanModel> getAllRujukan() throws ParseException, JsonParseException, JsonMappingException, IOException;
	List<PasienRujukanDetail> getAllPasienRujukan()
			throws ParseException, JsonParseException, JsonMappingException, IOException;
	void changeRujukan(RujukanRawatJalanModel pasienRujuk, int statusLama) throws ParseException;

}
