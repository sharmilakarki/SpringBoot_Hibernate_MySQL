package com.sharmila.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharmila.domain.MsiSdn;
import com.sharmila.domain.SmsLog;
import com.sharmila.manager.MsiSdnManager;
import com.sharmila.manager.SmsManager;

@RequestMapping("/sms")
@RestController
public class SmsController {
	 
	@Autowired
	private SmsManager smsManager;  
	
	@Autowired
	private MsiSdnManager msiSdnManager;
	
	@PostMapping()
	private SmsLog sendSms(@RequestBody SmsLog smsLog){
		return	smsManager.sendSms(smsLog);
	}
	
	@GetMapping
	private List<MsiSdn> getMSISDN() {
		return this.msiSdnManager.getAll();
	}
}
