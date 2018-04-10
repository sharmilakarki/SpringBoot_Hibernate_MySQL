package com.sharmila.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharmila.domain.Students;

public interface StudentsRepository extends JpaRepository<Students, Integer> {

}
