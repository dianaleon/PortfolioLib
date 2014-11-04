package com.portfolio.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import com.portfolio.asyncTask.GetMediaAsyncTask;
import com.portfolio.asyncTask.GetPortfolioAsyncTask;
import com.portfolio.handler.AsyncTaskHandler;
import com.portfolio.listener.IMediaListener;
import com.portfolio.listener.IPortfolioListener;
import com.portfolio.model.db.dao.MediaDAO;
import com.portfolio.model.entities.Media;
import com.portfolio.model.entities.Portfolio;
import com.portfolio.model.entities.component.BackgroundObject;
import com.portfolio.model.interfaces.IClient;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.IPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.IGeneralInfo;

public class PortfolioModel {

	private static PortfolioModel instance = null;

	private Portfolio portfolio;
	private Context context;

	private PortfolioModel(Context context) {
		this.context = context;
	}

	public static PortfolioModel getInstance(Context context) {
		if (instance == null) {
			instance = new PortfolioModel(context);
		}
		return instance;
	}

	public void getPortfolio(final IPortfolioListener callback) {
		GetPortfolioAsyncTask task = new GetPortfolioAsyncTask(context,
				new AsyncTaskHandler(context) {

					@Override
					public void acceptRequest(Message msg) {
						Portfolio portfolio = (Portfolio) msg.obj;
						PortfolioModel.instance.portfolio = portfolio;
						callback.onPortfolioReady();
					}
					
					@Override
					public void errorRequest(Message msg) {
						callback.errorGetPortfolio();
					}
				});
		task.exec();
	}

	public int getNumberPages() {
		return portfolio.getNumberPages();
	}

	public IPage getPageInfo(int numberPage) {
		return portfolio.getPage(numberPage);
	}
	
	public List<String> getPagesTitles() {
		List<String> names = new ArrayList<String>();
		if (portfolio != null) {
			Collection<IPage> pages = portfolio.getPages();
			Iterator<IPage> itePages = pages.iterator();
			while (itePages.hasNext()) {
				IPage page = (IPage) itePages.next();
				names.add(page.getTitle());				
			}
		}
		return names;
	}
	
	public List<Integer> getPagesPositions() {
		List<Integer> positions = new ArrayList<Integer>();
		if (portfolio != null) {
			Collection<IPage> pages = portfolio.getPages();
			Iterator<IPage> itePages = pages.iterator();
			while (itePages.hasNext()) {
				IPage page = (IPage) itePages.next();
				positions.add(page.getPosition());				
			}
		}
		Collections.sort(positions);
		return positions;
	}
	
	public List<BackgroundObject> getPagesItemBackground() {
		List<BackgroundObject> backgrounds = new ArrayList<BackgroundObject>();
		if (portfolio != null) {
			Collection<IPage> pages = portfolio.getPages();
			Iterator<IPage> itePages = pages.iterator();
			while (itePages.hasNext()) {
				IPage page = (IPage) itePages.next();
				backgrounds.add(page.getItemMenuBackground());				
			}
		}
		return backgrounds;
	}

	public void getMedia(final IMediaListener callback, final String url) {
		int index = url.lastIndexOf("/");
		String name = url.substring(index+1);
		Media media = null;
		try {
			media = MediaDAO.getInstanced(context).getByUrl(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (media != null) {
			String path = media.getPath();
			getImageFromFile(callback, path);
		} else {
			GetMediaAsyncTask task = new GetMediaAsyncTask(context,
					new AsyncTaskHandler(context) {

						@Override
						public void acceptRequest(Message msg) {
							String path = (String) msg.obj;
							saveMediaDBandGetImage(callback, url, path);
						}
					}, url, name);
			task.exec();
		}
	}
	
	private void saveMediaDBandGetImage(
			IMediaListener callback, String url, String path) {
		Media media = new Media();
		media.setPath(path);
		media.setUrl(url);
		MediaDAO mediaDAO = MediaDAO.getInstanced(context);
		try {
			mediaDAO.insertAndUpdate(media);
			getImageFromFile(callback, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getImageFromFile(IMediaListener callback, String path) {
		if (path == null)
			return;
		File imgFile = new File(path);
		if (imgFile.exists()) {
			Bitmap myBitmap = BitmapFactory.decodeFile(imgFile
					.getAbsolutePath());
			callback.onImageReady(myBitmap);
		}
	}
	
	public IMenu getPorfolioMenu() {
		return this.portfolio.getMenu();
	}
	
	public ITheme getTheme() {
		return this.portfolio.getTheme();
	}
	
	public IGeneralInfo getGeneralInformation() {
		return this.portfolio.getGeneralInfo();
	}
	
	public IClient getClient() {
		return this.portfolio.getClient();		
	}
}
