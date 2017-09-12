package com.phunware.WeatherApp.WeatherAPI.DataCollector;


/* Author Name: Lakshmi G for Phunware Interview*/



public interface WeatherDataCollectorService {

	WeatherDataPOJO getWeatherData(String zipCode);

	void refreshAllWeatherData();

	void fetchTexasWeather();

}