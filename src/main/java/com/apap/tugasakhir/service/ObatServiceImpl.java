package com.apap.tugasakhir.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugasakhir.model.ObatModel;
import com.apap.tugasakhir.repository.ObatDb;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
	@Autowired
	ObatDb obatDb;
	
	@Override
	public Optional<ObatModel> findByNama(String nama){
		return obatDb.findByNama(nama);
	}
	
	@Override
	public void save(ObatModel obat){
		obatDb.save(obat);
	}
	
	@Override
	public List<ObatModel> getListObat(){
		return obatDb.findAll();
	}
}
