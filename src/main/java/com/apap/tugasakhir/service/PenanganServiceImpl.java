package com.apap.tugasakhir.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.repository.PenangananDb;

@Service
@Transactional
public class PenanganServiceImpl implements PenangananService {
	
	@Autowired
	private PenangananDb penangananDb;
	
	
	@Override
	public List<PenangananModel> getPenangananList() {
		return penangananDb.findAll();
	}
}
