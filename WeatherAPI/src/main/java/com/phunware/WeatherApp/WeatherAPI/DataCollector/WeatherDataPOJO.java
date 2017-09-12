package com.phunware.WeatherApp.WeatherAPI.DataCollector;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/* Author Name: Lakshmi G for Phunware Interview*/

@JsonIgnoreProperties(ignoreUnknown=true)

public class WeatherDataPOJO implements Serializable {

	@JsonProperty("main")
	private static final long serialVersionUID = 1L;
	
	private double temp;
	private double pressure;
	private double humidity;
	private double temp_min;
	private double temp_max;
	public WeatherDataPOJO() {
	}
	public double getTemp() {
		return temp;
	}
	@JsonProperty("temp")
	public void setTemp(double temp) {
		this.temp = temp;
	}
	@JsonProperty("pressure")
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getHumidity() {
		return humidity;
	}
	@JsonProperty("humidity")
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getTemp_min() {
		return temp_min;
	}
	@JsonProperty("temp_min")
	public void setTemp_min(double temp_min) {
		this.temp_min = temp_min;
	}
	public double getTemp_max() {
		return temp_max;
	}
	@JsonProperty("temp_max")
	public void setTemp_max(double temp_max) {
		this.temp_max = temp_max;
	}
	
	 @Override
	    public String toString() {
	        return "WeatherDataPOJO [temp=" + temp + ", pressure=" + pressure + ", humidity=" + humidity + ", temp_min=" + temp_min + ", temp_max="
	                + temp_max + "]";
	    }
}
