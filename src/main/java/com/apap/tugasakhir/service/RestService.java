package com.apap.tugasakhir.service;

import java.util.List;

import org.json.simple.parser.ParseException;

import com.apap.tugasakhir.rest.PasienDetail;

public interface RestService {

	List<PasienDetail> parseAllPasien(String data) throws ParseException;

	String getRest(String url) throws ParseException;

	PasienDetail parsePasien(String data) throws ParseException;
	
}
