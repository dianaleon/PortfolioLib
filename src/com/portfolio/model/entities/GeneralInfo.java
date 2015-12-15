package com.portfolio.model.entities;

import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.model.interfaces.IGeneralInfo;

public class GeneralInfo implements IGeneralInfo {
	private String appName;
	
	
	public GeneralInfo(String appName) {		
			this.appName= appName;
	}
	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return this.appName;
	}
	

}
