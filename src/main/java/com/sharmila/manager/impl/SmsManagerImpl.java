package com.sharmila.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharmila.domain.LoadKannelUtils;
import com.sharmila.domain.MsiSdn;
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
		return this.loadKannelUtils.sendFakeSms(smsLog);

	}

	@Override
	public List<SmsLog> getAllLogs() {
		return this.smsRepository.findAll();
	}
	/**
	 * <p>Save the smsLog in the database</p>
	 */
	@Override
	public SmsLog saveSentSms(SmsLog smsLog) {
		return this.smsRepository.save(smsLog);
	}

	@Override
	public String sendSmsToBulk(List<MsiSdn> msisdn,SmsLog smsLog) {
		List<SmsLog> msiSdnList=new ArrayList<SmsLog>();
		
		for(MsiSdn msi:msisdn){
			smsLog.setRecipient(msi.getMsiSdn());
			msiSdnList.add(this.saveSentSms(this.sendSms(smsLog)))	;
		}
		return String.valueOf(msiSdnList.size());
	}

}
