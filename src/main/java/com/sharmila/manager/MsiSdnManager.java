package com.sharmila.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharmila.domain.MsiSdn;
import com.sharmila.domain.SmsLog;

public interface MsiSdnManager {
	public List<MsiSdn> getAll();
}
