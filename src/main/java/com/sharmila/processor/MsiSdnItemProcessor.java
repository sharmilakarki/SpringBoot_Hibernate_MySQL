package com.sharmila.processor;

import org.springframework.batch.item.ItemProcessor;

import com.sharmila.domain.MsiSdn;

public class MsiSdnItemProcessor implements ItemProcessor<MsiSdn,MsiSdn>{

	@Override
	public MsiSdn process(final MsiSdn item) throws Exception {
		final MsiSdn msiSdn=new MsiSdn(item.getMsiSdn());
		
		return msiSdn;
	}

}
