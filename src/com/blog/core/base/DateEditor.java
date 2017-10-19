package com.blog.core.base;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport{

	private final DateFormat[] dateFormat = { 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd"), 
			new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK),
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
			};

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		}else {
			Date parse = null;
			for (int i = 0; i < dateFormat.length; i++) {
				try {
					parse = this.dateFormat[i].parse(text);
				} catch (ParseException ex) {
					continue;
				}
				break;
			}
			if (parse != null) {
				setValue(parse);
			} else {
				throw new IllegalArgumentException("时间参数装换异常: " + text);
			}
		}
	}

	@Override
	public String getAsText() {
		Date value = (Date) getValue();
		return value.getTime() + "";
	}
	
}
