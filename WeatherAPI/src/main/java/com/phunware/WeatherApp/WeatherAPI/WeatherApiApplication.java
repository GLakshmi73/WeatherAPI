package com.phunware.WeatherApp.WeatherAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.phunware.WeatherApp.WeatherAPI.DataCollector.WeatherDataCollectorService;

@EnableCaching
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@Import(value=WeatherAPIApplicationConfig.class)
public class WeatherApiApplication {
	@ExceptionHandler(value = { Exception.class })
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(WeatherApiApplication.class, args);
		context.getBean(WeatherDataCollectorService.class).fetchTexasWeather();
		//SpringApplication.run(WeatherApiApplication.class, args);
	} 

}