package com.apap.tugasakhir.service;

import java.util.ArrayList;
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
			if (results.size() > 0) {
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

}
