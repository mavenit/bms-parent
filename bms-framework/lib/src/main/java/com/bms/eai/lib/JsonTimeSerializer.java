package com.bms.eai.lib;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class JsonTimeSerializer extends JsonSerializer<Date> {

	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(DateUtils.TIME_FORMAT);
	
	@Override
	public void serialize(Date time, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		String formattedDate = timeFormat.format(time);
		gen.writeString(formattedDate);
	}

	 
}
