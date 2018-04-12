package com.sharmila.controller;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.sharmila.domain.MsiSdn;


public class TestBatch {

	public static void main(String args[]){
		try {
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false","root","root");
			Statement st=connection.createStatement();
		
			List<MsiSdn> readMsiList;
			try {
				readMsiList = readCSVfileAndUpdate();
				for(MsiSdn m:readMsiList){
					String query="insert into msisdn(msisdn) values ("+m.getMsiSdn()+")";
					st.addBatch(query);
				}

				st.executeBatch();
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<MsiSdn> readCSVfileAndUpdate() throws IOException {
		File file=new File("50M_msisdns992.csv");
		List<MsiSdn> msisdnList=new ArrayList<>();
		try {
			InputStream inputStream=new FileInputStream(file);
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
			msisdnList=bufferedReader.lines().skip(1).map(rawToMsiSdn).collect(Collectors.toList());
			
			bufferedReader.close();
			
			System.out.println("List Size "+msisdnList.size());
			return msisdnList;
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			throw new IOException();
		}
		
	}

	static Function<String, MsiSdn> rawToMsiSdn= new Function<String, MsiSdn>() {
		public MsiSdn apply(String line){
			MsiSdn msi=new MsiSdn.Builder().msiSdn(Long.parseLong(line)).build();
			return msi;
		}
	};
	
}
