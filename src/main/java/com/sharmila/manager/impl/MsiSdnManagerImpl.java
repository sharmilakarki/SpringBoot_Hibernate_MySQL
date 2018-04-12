package com.sharmila.manager.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharmila.domain.MsiSdn;
import com.sharmila.manager.MsiSdnManager;
import com.sharmila.repository.MsiSdnRepository;

@Service
public class MsiSdnManagerImpl  implements MsiSdnManager{

	@Autowired
	private MsiSdnRepository msiSdnRepository;
	
	private static final Logger logger=LoggerFactory.getLogger(MsiSdnManagerImpl.class);

	@Override
	public List<MsiSdn> getAll() {
		return this.msiSdnRepository.findAll();
	}


	@Override
	public List<MsiSdn> readCSVfileAndUpdate() throws IOException {
		File file=new File("50M_msisdns992.csv");
		List<MsiSdn> msisdnList=new ArrayList<>();
		try {
			InputStream inputStream=new FileInputStream(file);
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
			msisdnList=bufferedReader.lines().skip(1).map(rawToMsiSdn).collect(Collectors.toList());
			
			bufferedReader.close();
			boolean alreadyExist=false;	
			long lastIndex=this.msiSdnRepository.findAll().size();
			if(lastIndex==0){
				lastIndex=0;
			}else{
				lastIndex=lastIndex-1;
			}
			for (MsiSdn m : msisdnList.subList((int) lastIndex, msisdnList.size()-1)) {
				alreadyExist = this.checkMsiSdnAlreadyExist(m.getMsiSdn());
				if (alreadyExist == false) {
					this.msiSdnRepository.save(m);
				}
			}
			System.out.println("List Size "+msisdnList.size());
			
//			Iterable<MsiSdn> msiSdn=msisdnList;
//			logger.info(" before saving msisdn");
//			List<MsiSdn> response = this.msiSdnRepository.saveAll(msiSdn); 
//			logger.info(" after saving "+response.size());
			return msisdnList;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOException();
		}
		
	}

	Function<String, MsiSdn> rawToMsiSdn= new Function<String, MsiSdn>() {
		public MsiSdn apply(String line){
			MsiSdn msi=new MsiSdn.Builder().msiSdn(Long.parseLong(line)).build();
			return msi;
		}
	};

	@Override
	public MsiSdn getByMsiSdn(long msiSdn) {
		return this.msiSdnRepository.findByMsiSdn(msiSdn);
	}


	@Override
	public boolean checkMsiSdnAlreadyExist(long msiSdn) {
		MsiSdn msi=this.msiSdnRepository.findByMsiSdn(msiSdn);
		if(msi!=null){
			return true;
		}
		return false;
	}


}