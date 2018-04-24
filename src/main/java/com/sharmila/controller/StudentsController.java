package com.sharmila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharmila.domain.Students;
import com.sharmila.repository.StudentsRepository;

@RestController
@RequestMapping("/students")
public class StudentsController {

	@Autowired
	private StudentsRepository studentRepository;

	@GetMapping
	public Iterable<Students> getStudents(){
		return	this.studentRepository.findAll();
	}
}
