package com.apap.tugasakhir.service;


import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.rest.PemeriksaanDetail;

public interface PenangananService {

	List<PenangananModel> getPenangananList();



	void addPenanganan(PenangananModel penanganan);



	Map<Integer, String> getDataPemeriksaan() throws ParseException;



	String kirimPenanganan();



	String postPenanganan(PenangananModel penanganan) throws java.text.ParseException;





}
