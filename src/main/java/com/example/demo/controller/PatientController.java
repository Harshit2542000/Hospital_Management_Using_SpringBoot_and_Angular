package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.exception.PatientResourceNotFoundException;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	
	//get all patients
	
	@GetMapping("/patients")
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}
	
	//create patient rest api
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient)
	{
		return patientRepository.save(patient);
	}
	
	//get patient by id rest api
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
				Patient patient = patientRepository.findById(id)
						.orElseThrow(() -> new PatientResourceNotFoundException("Patient not exist with id :" + id));
				return ResponseEntity.ok(patient);
	}
	
	// update patient rest api
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails){
			Patient patient = patientRepository.findById(id)
					.orElseThrow(() -> new PatientResourceNotFoundException("Patient not exist with id :" + id));
			patient.setName(patientDetails.getName());
			patient.setVisitedDoctor(patientDetails.getVisitedDoctor());
			patient.setDateOfVisit(patientDetails.getDateOfVisit());
			patient.setAge(patientDetails.getAge());
			patient.setDoctorprescription(patientDetails.getDoctorprescription());
			
			Patient updatedPatient = patientRepository.save(patient);
			return ResponseEntity.ok(updatedPatient);
	}
	
	//delete patient rest api
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id){
		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientResourceNotFoundException("Patient not exist with id :" + id));
		patientRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
