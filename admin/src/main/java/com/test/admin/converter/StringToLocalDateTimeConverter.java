package com.test.admin.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime>{

	@Override
	public LocalDateTime convert(@NonNull String source) {
		DateTimeFormatter formmater = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a", Locale.ENGLISH);
		return LocalDateTime.parse(source, formmater);
	}

}
