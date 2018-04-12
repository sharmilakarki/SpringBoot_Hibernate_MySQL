package com.sharmila.controller;

import java.io.IOException;
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
	
	/**
	 * <p>One smsLog created after sending sms.</p>
	 * @param smsLog
	 * @return
	 */
	@PostMapping()
	private SmsLog sendSms(@RequestBody SmsLog smsLog){
		return	smsManager.sendSms(smsLog);
	}
	/**
	 * <p> send sms to all the recievers stored in database, first  retrieve the data and send the Request Body of SmsLog </p>
	 * @param smsLog
	 * @return
	 */
	@PostMapping("/to-all")
	private String sendSmsToAllUsers(@RequestBody SmsLog smsLog){
		List<MsiSdn> msisdn = this.msiSdnManager.getAll();
		
		return this.smsManager.sendSmsToBulk(msisdn, smsLog);
	}
	
	
	/**
	 * <p> This api is used to read CSV files and update the data in the correct tables </p>
	 */
	@GetMapping
	private void getMSISDN() {
		 try {
			 this.msiSdnManager.readCSVfileAndUpdate();
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}