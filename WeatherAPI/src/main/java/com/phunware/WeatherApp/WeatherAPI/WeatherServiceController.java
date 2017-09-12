package com.phunware.WeatherApp.WeatherAPI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.phunware.WeatherApp.WeatherAPI.DataCollector.*;



/* Author Name: Lakshmi G for Phunware Interview*/

@RestController
public class WeatherServiceController {
	Logger log =Logger.getLogger(WeatherServiceController.class);

	@Inject
	WeatherDataCollectorService weatherService;

	@GetMapping(path="/weather/{zipCode}")
	@ResponseBody
	@ExceptionHandler(value = { Exception.class })
	public  WeatherDataPOJO getWeatherData(
			@RequestParam(value="zipCode", defaultValue="78727") String zipCode) {
		log.debug("in WeatherService IMPL");
		return weatherService.getWeatherData(zipCode);
	}

	@GetMapping(path="/weather", params="zipCodes")
	@ResponseBody
	@ExceptionHandler(value = { Exception.class })
	public  List<WeatherDataPOJO> getWeatherDataMultipleZipCodes(
			@RequestParam(value="zipCodes") List<String> zipCodes) {
		List<WeatherDataPOJO> weatherData = new ArrayList<>();
		for(String zip : zipCodes){
			weatherData.add(weatherService.getWeatherData(zip));
		}
		return weatherData;
	}

}
