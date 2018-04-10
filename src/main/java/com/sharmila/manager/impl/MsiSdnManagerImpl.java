package com.sharmila.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharmila.domain.MsiSdn;
import com.sharmila.manager.MsiSdnManager;
import com.sharmila.repository.MsiSdnRepository;

@Service
public class MsiSdnManagerImpl  implements MsiSdnManager{

	@Autowired
	private MsiSdnRepository msiSdnRepository;
	

	@Override
	public List<MsiSdn> getAll() {
		return this.msiSdnRepository.findAll();
	}

}
