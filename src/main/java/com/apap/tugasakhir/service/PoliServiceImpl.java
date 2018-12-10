package com.apap.tugasakhir.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugasakhir.model.HariPoliModel;
import com.apap.tugasakhir.model.PoliModel;
import com.apap.tugasakhir.repository.HariPoliDb;
import com.apap.tugasakhir.repository.PoliDb;

@Service
@Transactional
public class PoliServiceImpl implements PoliService {

	@Autowired
	private PoliDb poliDb;
	@Autowired
	private HariPoliDb db;
	
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
	
	@Override
	public void createHariPoli(String[] array, PoliModel poli) {
		for(String hari : array) {
			HariPoliModel hariPoli = new HariPoliModel();
			hariPoli.setPoli(poli);
			hariPoli.setHari(hari);
			
			db.save(hariPoli);
		}
	}
	public void resetHariPoli(PoliModel poli) {
		System.out.println(poli.getListHariPoli());
		for(HariPoliModel hariPoli : poli.getListHariPoli()) {
			System.out.println(hariPoli.getId());
			db.deleteById(hariPoli.getId());
			System.out.println(hariPoli.getId());
			
		}
	}

	@Override
	public ArrayList<String> turnIntoString(PoliModel poli) {
		// TODO Auto-generated method stub
		ArrayList<String> tempArray = new ArrayList<>();
		for(HariPoliModel objek : poli.getListHariPoli()) {
			tempArray.add(objek.getHari());
		}
		return tempArray;
	}

}
