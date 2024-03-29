package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	Optional<Patient> findByName(String name);
}
