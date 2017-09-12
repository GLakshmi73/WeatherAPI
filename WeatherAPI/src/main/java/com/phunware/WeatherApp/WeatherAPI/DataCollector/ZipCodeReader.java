package com.phunware.WeatherApp.WeatherAPI.DataCollector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/* Author Name: Lakshmi G for Phunware Interview*/
public class ZipCodeReader {
	public List<String> getZipCode(){
         ClassLoader classLoader=getClass().getClassLoader();
         
		String csvFile = "Zipcode.csv";
		String line = "";
		List<String> zipCodes =new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(classLoader.getResource(csvFile).getFile()))) {

			while ((line = br.readLine()) != null) {

				zipCodes.add(line);
				
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zipCodes;
	}
}

