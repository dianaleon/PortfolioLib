package com.portfolio.model.interfaces;

import com.portfolio.model.entities.component.BackgroundObject;

public interface ITheme {

	public String getCode();
	
	public String getUrlImages();
	
	public String getHomeImage();
	
	public String getHomeImageClear();
	
	public BackgroundObject getBackground();
	
	public BackgroundObject getTitleBarBackground();

	public BackgroundObject getMenuBackground();

	public BackgroundObject getMenuItemBackground();
	
	public String getHeaderLayout();
	
	public String getFooterLayout();

}
