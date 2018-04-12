package com.sharmila.manager;

import java.util.List;

import com.sharmila.domain.MsiSdn;
import com.sharmila.domain.SmsLog;

public interface SmsManager {
	public List<SmsLog> getAll();
	public SmsLog sendSms(SmsLog smsLog);
	public List<SmsLog> getAllLogs();
	public SmsLog saveSentSms(SmsLog smsLog);
	public String sendSmsToBulk(List<MsiSdn> msiSdn,SmsLog smsLog);
}