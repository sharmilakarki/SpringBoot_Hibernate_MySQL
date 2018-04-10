package com.sharmila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharmila.domain.Students;
import com.sharmila.repository.StudentsRepository;

@RestController
@RequestMapping("/")
public class StudentsController {

	@Autowired
	private StudentsRepository studentRepository;

	@GetMapping
	public Iterable<Students> getStudents(){
		System.out.println(" th is is");
		return	 this.studentRepository.findAll();
	}
}
