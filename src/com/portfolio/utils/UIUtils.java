package com.portfolio.utils;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.widget.TextView;

public class UIUtils {

	public static void setGradient(View view, String startColor, String endColor, String orientation) {
		if (startColor != null && endColor != null) {
			GradientDrawable g = new GradientDrawable(Orientation.TOP_BOTTOM, new int[] { Color.parseColor(startColor), Color.parseColor(endColor)});
			g.setGradientType(GradientDrawable.LINEAR_GRADIENT);
			if (orientation != null) {
				try {
					g.setGradientRadius(Float.parseFloat(orientation));
				} catch (Exception e) {
					
				}
			}
			view.setBackgroundDrawable(g);
		}
	}
	
	public static void setTextColor(TextView view, String textColor) {
		if (textColor != null && textColor.length() > 0) {
			view.setTextColor(Color.parseColor(textColor));
		}
	}
}
