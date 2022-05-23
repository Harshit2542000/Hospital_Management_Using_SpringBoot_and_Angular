package com.example.demo.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name="patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name= "name")
	private String name;
	@Column(name="visiteddoctor")
	private String visitedDoctor;
	@Column(name="dateofvisit")
	private String dateOfVisit;
	@Column(name="age")
	private int age;
	@Column(name="doctorprescription")
	private String doctorprescription;
	
	public Patient() {
		
	}
	
	public Patient(String name, String visitedDoctor, String dateOfVisit, int age, String doctorprescription) {
		super();
		this.name = name;
		this.visitedDoctor = visitedDoctor;
		this.dateOfVisit = dateOfVisit;
		this.age = age;
		this.doctorprescription = doctorprescription;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisitedDoctor() {
		return visitedDoctor;
	}

	public void setVisitedDoctor(String visitedDoctor) {
		this.visitedDoctor = visitedDoctor;
	}

	public String getDateOfVisit() {
		return dateOfVisit;
	}

	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDoctorprescription() {
		return doctorprescription;
	}

	public void setDoctorprescription(String doctorprescription) {
		this.doctorprescription = doctorprescription;
	}

	
}
