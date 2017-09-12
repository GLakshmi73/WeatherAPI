package com.phunware.WeatherApp.WeatherAPI.DataCollector;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/* Author Name: Lakshmi G for Phunware Interview*/
@Service
public class WeatherDataCollectorServiceImpl implements WeatherDataCollectorService {

	Logger logger = Logger.getLogger(WeatherDataCollectorServiceImpl.class);

	@Inject
	@Named("appRestClient")
	private RestTemplate restTemplate=new RestTemplate();



	@Override
	@Cacheable(value="weatherDataCache", cacheManager="weatherCacheManager")
	@ExceptionHandler(value = { Exception.class })
	public WeatherDataPOJO getWeatherData(String zipCode) 
	{
		logger.info("Before Hitting the Resttemplate");
		WeatherDataPOJO weatherDataPOJO=new WeatherDataPOJO();
		JsonNode weatherDataPOJO1=restTemplate.getForObject("http://samples.openweathermap.org/data/2.5/weather?zip={zipCode},us&appid=b1b15e88fa797225412429c1c50c122a1",JsonNode.class,zipCode);
		logger.info("After Hitting the Resttemplate");
		ObjectMapper mapper = new ObjectMapper();
		try {
			weatherDataPOJO = mapper.treeToValue(weatherDataPOJO1.get("main"), WeatherDataPOJO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		logger.info("for ZipCode"+zipCode);
		logger.info("The Humidity is"+weatherDataPOJO.getHumidity());
		logger.info("The Pressure is"+weatherDataPOJO.getPressure());
		logger.info("The Temp is"+weatherDataPOJO.getTemp());
		logger.info("THe Max_Temp is"+weatherDataPOJO.getTemp_max());
		logger.info("The Min_Temp is"+weatherDataPOJO.getTemp_min());
		return weatherDataPOJO;

	}

	
	@Override
	@CacheEvict(value = "weatherDataCache", allEntries = true)
	public void refreshAllWeatherData() {
	} 

	
	@Override
	@ExceptionHandler(value = { Exception.class })
	public void fetchTexasWeather() {
		List<String> zipCodes = new ArrayList<>();
		ZipCodeReader reader=new ZipCodeReader();
		zipCodes.addAll(reader.getZipCode());

		for(String zipCode : zipCodes){
			getWeatherData(zipCode);
		}
	}

}
