package com.apap.tugasakhir.service;

import org.json.simple.parser.ParseException;

import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.rest.PasienRujukanDetail;

public interface RujukanService {
	RujukanRawatJalanModel getRujukanById(Long id);
	void changeRujukan(PasienRujukanDetail pasien, String status) throws ParseException;

}
