package com.sharmila.manager;

import java.io.IOException;
import java.util.List;

import com.sharmila.domain.MsiSdn;

public interface MsiSdnManager {
	public List<MsiSdn> getAll();
	public MsiSdn getByMsiSdn(long msiSdn);
	public List<MsiSdn> readCSVfileAndUpdate() throws IOException;
	public boolean checkMsiSdnAlreadyExist(long msiSdn);
}