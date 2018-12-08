package com.apap.tugasakhir.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.repository.JadwalPoliDb;
import com.apap.tugasakhir.rest.DokterDetail;
import com.apap.tugasakhir.controller.*;


@Service
@Transactional
public class JadwalPoliServiceImpl implements JadwalPoliService	 {
	@Autowired
	private JadwalPoliDb jadwalPoliDb;
	
	@Autowired
	private RestService restService;
	
	@Override
	public void addJadwalPoli(JadwalPoliModel jadwalPoli) {
		jadwalPoliDb.save(jadwalPoli);
	}

	@Override
	public List<JadwalPoliModel> getAllJadwalPoli() {
		// TODO Auto-generated method stub
		return jadwalPoliDb.findAll();
	}
	
	

	@Override
	public Map<Integer, DokterDetail> getDoctor() throws ParseException {
		Map<Integer, DokterDetail> mapDokter = new HashMap<Integer, DokterDetail>();
		List<JadwalPoliModel> listJadwalPoli = jadwalPoliDb.findAll();
		for (JadwalPoliModel jadwalPoli : listJadwalPoli){
			System.out.println("ini jadwal poli id "+String.valueOf(jadwalPoli.getId()));
			DokterDetail dokter = restService.getDokter(jadwalPoli.getIdDokter());
			System.out.println("Cek dokter "+dokter.getNama());
			mapDokter.put(jadwalPoli.getId(), dokter);
		}
		for (Integer idPoli: mapDokter.keySet()){
			System.out.println(mapDokter.get(idPoli).getNama());
		} 
		System.out.println(mapDokter.keySet());
		return mapDokter;
	}

	@Override
	public void updateJadwalPoli(JadwalPoliModel jadwalPoliNew, int id) {
		JadwalPoliModel jadwalPoliUpdate = jadwalPoliDb.findById(id);
		jadwalPoliUpdate.setIdDokter(jadwalPoliNew.getIdDokter());
		jadwalPoliUpdate.setPoli(jadwalPoliNew.getPoli());
		jadwalPoliUpdate.setTanggal(jadwalPoliNew.getTanggal());
		jadwalPoliUpdate.setJamMulai(jadwalPoliNew.getJamMulai());
		jadwalPoliUpdate.setJamSelesai(jadwalPoliNew.getJamSelesai());
		jadwalPoliDb.save(jadwalPoliUpdate);
	}

	@Override
	public JadwalPoliModel getById(int id) {

		return jadwalPoliDb.findById(id);
	}
	
}
