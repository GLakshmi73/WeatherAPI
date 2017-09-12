package com.phunware.WeatherApp.WeatherAPI;

import java.util.Collections;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;


/* Author Name: Lakshmi G for Phunware Interview*/
@EnableCaching
@Configuration
@ComponentScan(basePackages="com.phunware.WeatherApp.WeatherAPI")
public class WeatherAPIApplicationConfig {


	@Bean(name = "appRestClient")
	public RestTemplate getRestClient() {
		RestTemplate restClient = new RestTemplate(
	    new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		restClient.setInterceptors(Collections.singletonList((request, body, execution) 
				                                -> {return execution.execute(request, body);
		}));

		return restClient;
	}

	@Bean(name="weatherCacheManager")
	@ExceptionHandler(value = { Exception.class })
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean(name="weatherCacheFactory")
	@ExceptionHandler(value = { Exception.class })
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
		factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factory.setShared(true);
		return factory;
	}



}

