//Deepak
//package com.covidpersona.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.VaccinationDetails.domain.Patient;
//import com.example.VaccinationDetails.repository.VaccinationRepository;
//
//@Service
//public class VaccinationService {
//	@Autowired
//    private VaccinationRepository repo;
//	
//	public List<Patient> listAll() {
//        return repo.findAll();
//    }
//     
//    public void save(Patient std) {
//        repo.save(std);
//    }
//     
//    public Patient get(long id) {
//        return repo.findById(id).get();
//    }
//     
//    public void delete(long id) {
//        repo.deleteById(id);
//    }
// 
//
//}
