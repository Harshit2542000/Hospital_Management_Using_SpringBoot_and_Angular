package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientRepositoryTests {
	
	@Autowired
	private PatientRepository patientRepository;
	
	//jUnit Test for savePatient
	@Test
	@Order(1)
	@Rollback(value = false)
	public void savePatientTest()
	{
		Patient patient = new Patient("Suraj Advani","Raman Sharma","13th October, 2019", 32, 
				"Apply Salactin Paint on Callus Area Once a day for 3 days");

        patientRepository.save(patient);

        Assertions.assertThat(patient.getId()).isGreaterThan(0);
	}
	//jUnit Test for getPatient
	@Test
	@Order(2)
	public void getPatientTest(){

        Patient patient = patientRepository.findById(104L).get();

        Assertions.assertThat(patient.getId()).isEqualTo(104L);

    }
	//jUnit Test for getListOfPatients
	@Test
	@Order(3)
	public void getListOfPatientsTest(){

        List<Patient> patients = patientRepository.findAll();

        Assertions.assertThat(patients.size()).isGreaterThan(0);

    }
	//jUnit Test for UpdatePatient
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updatePatientTest(){

        Patient patient = patientRepository.findById(101L).get();

        patient.setName("Vinayak Ashwin");

        Patient patientUpdated =  patientRepository.save(patient);

        Assertions.assertThat(patientUpdated.getName()).isEqualTo("Vinayak Ashwin");

    }
	//jUnit Test for deletePatient
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deletePatientTest(){

        Patient patient = patientRepository.findById(100L).get();

        patientRepository.delete(patient);

        //patientRepository.deleteById(100L);

        Patient patient1 = null;

        Optional<Patient> optionalPatient = patientRepository.findByName("Kashish Bansal");

        if(optionalPatient.isPresent()){
            patient1 = optionalPatient.get();
        }

        Assertions.assertThat(patient1).isNull();
    }

}
