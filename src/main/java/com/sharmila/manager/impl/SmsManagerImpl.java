package com.sharmila.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharmila.domain.LoadKannelUtils;
import com.sharmila.domain.SmsLog;
import com.sharmila.manager.SmsManager;
import com.sharmila.repository.SmsRepository;

@Service
public class SmsManagerImpl implements SmsManager {

	@Autowired
	private SmsRepository smsRepository;
	
	@Autowired
	private LoadKannelUtils loadKannelUtils;
	
	private static final Logger logger=LoggerFactory.getLogger(SmsManagerImpl.class);
	
	@Override
	public List<SmsLog> getAll() {
		return smsRepository.findAll();
	}

	@Override
	public SmsLog sendSms(SmsLog smsLog) {
		SmsLog sms = this.loadKannelUtils.sendFakeSms(smsLog);
		if (sms != null) {
			logger.info("sms is not null");
			smsRepository.save(sms);
		}
		return sms;

	}

	@Override
	public List<SmsLog> getAllLogs() {
		// TODO Auto-generated method stub
		return null;
	}

}
