package com.sharmila.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.sharmila.domain.SmsLog;

public interface SmsRepository extends JpaRepository<SmsLog, Integer>  {
	
}
