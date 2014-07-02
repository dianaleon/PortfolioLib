package com.portfolio.utils;

public class Config {

	public static final String IMAGES_EXTERNAL_PATH = "/Portfolio/Images";
	public static final String IMAGES_INTERNAL_PATH = "/Images";
	
	public static final String JSON_EXTERNAL_PATH = "/Portfolio/JSON";
	public static final String JSON_INTERNAL_PATH = "/JSON";
	
	public static String URL = "http://diana4love.com/AppsWorld/appsworld.json";

	public final static String getUrl() {
//		return "http://www.appsworld.crealogica.eu/webServices/getAppById.php?id_app=1";
//		return "http://www.appsworld.crealogica.eu/webServices/getAppById.php?id_app=1718";
		return URL;
		
	}

	
}
