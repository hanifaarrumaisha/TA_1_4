package com.apap.tugasakhir.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apap.tugasakhir.model.JadwalPoliModel;
import com.apap.tugasakhir.model.PenangananModel;
import com.apap.tugasakhir.model.RujukanRawatJalanModel;
import com.apap.tugasakhir.repository.JadwalPoliDb;
import com.apap.tugasakhir.repository.PoliDb;
import com.apap.tugasakhir.repository.RujukanRawatJalanDb;
import com.apap.tugasakhir.rest.PasienRujukanDetail;
import com.apap.tugasakhir.rest.Setting;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
@Transactional

public class RujukanServiceImpl implements RujukanService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestService restService;

	@Autowired
	private RujukanRawatJalanDb rujukanDb;

	@Autowired
	private JadwalPoliDb jadwalPoliDb;
	
	@Autowired
	private PoliDb poliDb;

	@Override
	public RujukanRawatJalanModel getRujukanById(Long id) {
		// TODO Auto-generated method stub
		return rujukanDb.getOne(id);
	}

	@Override
	public void changeRujukan(PasienRujukanDetail pasien, String status) throws ParseException {
		int idStatusNow = 0;
//		RujukanRawatJalanModel rujukanChange = rujukanDb.getOne(id);
		if (status.equalsIgnoreCase("Mendaftar di Rawat Jalan"))
			idStatusNow = 7;
		else if (status.equalsIgnoreCase("Berada di Rawat Jalan"))
			idStatusNow = 8;
		else if (status.equalsIgnoreCase("Selesai di Rawat Jalan"))
			idStatusNow = 9;
		if (pasien.getStatusPasien().getId() < idStatusNow && idStatusNow - pasien.getStatusPasien().getId() == 1) {
			pasien.getStatusPasien().setId(idStatusNow);
			pasien.getStatusPasien().setJenis(status);
			//manggil API dari siAppointment untuk update status
			//restService.updateStatusPasien(pasien);
			restService.updateStatusPasien(pasien);
			System.out.println("masuk update status berhasil");
			System.out.println(pasien.getStatusPasien().getId());
		}
	}

	@Override
	public void validateRujukan(PasienRujukanDetail pasien) {
		if (!rujukanDb.findByIdPasien(pasien.getId()).isPresent()) {
			// get jadwal terdekat
			System.out.println(pasien.getPoliRujukan().getId());
			
			List<JadwalPoliModel> results = jadwalPoliDb.findByPoliIdAndTanggalGreaterThanEqualOrderByTanggalDesc(pasien.getPoliRujukan().getId(), pasien.getTanggalRujukan());
			if (results.size() > 0 && pasien.getPoliRujukan()!=null) {
				RujukanRawatJalanModel rujukan = new RujukanRawatJalanModel();
				rujukan.setIdPasien(pasien.getId());
				rujukan.setJadwalPoli(results.get(0));
				rujukan.setNamaPasien(pasien.getNama());
				rujukan.setStatus(pasien.getStatusPasien().getId());
				rujukan.setListPenanganan(new ArrayList<PenangananModel>());
				rujukan.setTanggalRujuk(pasien.getTanggalRujukan());
				rujukanDb.save(rujukan);
				System.out.println("Tanggal rujukan - tanggal terdekat");
				System.out.println(pasien.getId());
				System.out.println(pasien.getTanggalRujukan());
				System.out.println(results.get(0).getTanggal());
			} else {
				System.out.println("ga ada yg terdekat");
				System.out.println(pasien.getTanggalRujukan());
			}
		}
	}
	
	@Override
	public List<PasienRujukanDetail> getAllPasienRujukan() throws ParseException, JsonParseException, JsonMappingException, IOException {
		// get from SiAppointment
				String urlApp = Setting.siApp+"/4/getAllPasienRawatJalan/";
				String responseApp = restService.getRest(urlApp);
				ArrayList<PasienRujukanDetail> listPasien = (ArrayList<PasienRujukanDetail>) restService.parseListPasien(responseApp);
				
				// get from SiIGD
				String urlIGD = Setting.siIGD+"/rujukan";
				String responseIGD = restService.getRest(urlIGD);
				ArrayList<PasienRujukanDetail> listPasienIGD = (ArrayList<PasienRujukanDetail>) restService.parsePasienIGD(responseIGD);
				listPasien.addAll(listPasienIGD);
				for (PasienRujukanDetail pasien : listPasien) {
					validateRujukan(pasien);
				}
		return listPasien;
	}
	
	@Override
	public ArrayList<Object> getAllRujukan() throws ParseException, JsonParseException, JsonMappingException, IOException{
		List<RujukanRawatJalanModel> allRujukan = rujukanDb.findAll();
		System.out.println("sizenya "+allRujukan.size());
		ArrayList<Object> output = new ArrayList<Object>();
		HashMap<Integer, RujukanRawatJalanModel> mapRujukan = new HashMap<Integer, RujukanRawatJalanModel>();
		
		String urlApp = Setting.siApp+"/getPasien?listId=";
		
		for (RujukanRawatJalanModel rujukan : allRujukan) {
			mapRujukan.put(rujukan.getIdPasien(), rujukan);
			urlApp = urlApp.concat(rujukan.getIdPasien()+",");
			System.out.println(urlApp);
		}
		urlApp = urlApp.substring(0, urlApp.length()-1).concat("&resultType=List");
		System.out.println(urlApp);
		String responseApp = restService.getRest(urlApp);
		System.out.println(responseApp);
		ArrayList<PasienRujukanDetail> listPasien = (ArrayList<PasienRujukanDetail>) restService.parseListPasien(responseApp);
		System.out.println("coba nama poli rujukan: " + listPasien.get(0).getPoliRujukan().getNama());
		System.out.println("coba nama poli rujukan: " + listPasien.get(1).getPoliRujukan().getNama());
		System.out.println("coba nama poli rujukan: " + listPasien.get(2).getPoliRujukan().getNama());
		System.out.println("coba nama poli rujukan: " + listPasien.get(3).getPoliRujukan().getNama());
		System.out.println("coba nama poli rujukan: " + listPasien.get(4).getPoliRujukan().getNama());
		output.add(mapRujukan);
		output.add(listPasien);
		return output;
	}
}
