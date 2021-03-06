package com.portfolio.model.entities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.portfolio.model.entities.component.ImageObject;
import com.portfolio.model.entities.component.TextObject;
import com.portfolio.model.entities.component.VideoObject;
import com.portfolio.model.interfaces.IPage;
import com.portfolio.model.interfaces.IPhotoTextPage;
import com.portfolio.model.interfaces.component.IPageObject;

public class TextTextPage extends Page implements IPhotoTextPage {

	public TextTextPage(Type type, JSONObject jsonObject, String layout) {
		super(jsonObject);
		this.layout = layout;
		this.type = type;
		this.type.setTypeValue(IPage.type_text_grid);
		this.objects = new ArrayList<IPageObject>();
		try {
			JSONArray data = jsonObject.getJSONArray("data");
			for (int index = 0; index < data.length(); index++) {
				JSONObject object = data.getJSONObject(index);
				if (((String) object.get("tipo")).equalsIgnoreCase("image")) {
					this.objects.add(new ImageObject(object));
				}
				if (((String) object.get("tipo")).equalsIgnoreCase("text")) {
					this.objects.add(new TextObject(object));
				}
				if (((String) object.get("tipo")).equalsIgnoreCase("video")) {
					this.objects.add(new VideoObject(object));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
