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

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorRepositoryTests {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	//jUnit Test for saveDoctor
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveDoctorTest()
	{
		Doctor doctor = new Doctor("Harish Rawat","Male","Dentist");

        doctorRepository.save(doctor);

        Assertions.assertThat(doctor.getId()).isGreaterThan(0);
	}
	//jUnit Test for getDoctor
	@Test
	@Order(2)
	public void getDoctorTest(){

        Doctor doctor = doctorRepository.findById(1L).get();

        Assertions.assertThat(doctor.getId()).isEqualTo(1L);

    }
	//jUnit Test for getListOfDoctors
	@Test
	@Order(3)
	public void getListOfDoctorsTest(){

        List<Doctor> doctors = doctorRepository.findAll();

        Assertions.assertThat(doctors.size()).isGreaterThan(0);

    }
	//jUnit Test for UpdateDoctor
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateDoctorTest(){

        Doctor doctor = doctorRepository.findById(9L).get();

        doctor.setName("Ritik Samson");

        Doctor doctorUpdated =  doctorRepository.save(doctor);

        Assertions.assertThat(doctorUpdated.getName()).isEqualTo("Ritik Samson");

    }
	//jUnit Test for deleteDoctor
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteDoctorTest(){

        Doctor doctor = doctorRepository.findById(2L).get();

        doctorRepository.delete(doctor);

        //doctorRepository.deleteById(2L);

        Doctor doctor1 = null;

        Optional<Doctor> optionalDoctor = doctorRepository.findByName("Shruti Garg");

        if(optionalDoctor.isPresent()){
            doctor1 = optionalDoctor.get();
        }

        Assertions.assertThat(doctor1).isNull();
    }

}
