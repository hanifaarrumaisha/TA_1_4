package com.apap.tugasakhir.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasakhir.model.PoliModel;
import com.apap.tugasakhir.repository.PoliDb;

@Service
@Transactional
public class PoliServiceImpl implements PoliService {

	@Autowired
	private PoliDb poliDb;
	
	@Override
	public void addPoli(PoliModel poli) {
		// TODO Auto-generated method stub
		poliDb.save(poli);
		
	}

	@Override
	public List<PoliModel> findAll() {
		// TODO Auto-generated method stub
		
		return poliDb.findAll();
	}

	@Override
	public PoliModel updatePoli(PoliModel poli) {
		// TODO Auto-generated method stub
		PoliModel poliToUpdate = poliDb.getOne(poli.getId());
		poliToUpdate.setNama(poli.getNama());
		poliToUpdate.setDeskripsi(poli.getDeskripsi());
		return poliToUpdate;
	}

	@Override
	public PoliModel getPoliById(Integer id) {
		// TODO Auto-generated method stub
		
		return poliDb.getOne(id);
	}

}
