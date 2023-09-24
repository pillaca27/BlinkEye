package net.royal.spring.framework.web.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class UDeserializers {
	// private static final String[] FORMATO_JSON_FECHA = {
	// "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSZ" };
	private static final String[] FORMATO_JSON_FECHA = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS" };
	private static final String FLAG_SI = "S";
	private static final String FLAG_NO = "N";

	private UDeserializers() {
	}

	public static class StringFlagDeserializer extends JsonDeserializer<String> {
		@Override
		public String deserialize(JsonParser parser, DeserializationContext context) throws IOException {
			return Boolean.TRUE.toString().equals(parser.getText()) ? FLAG_SI : FLAG_NO;
		}
	}

	public static class DateDeserializer extends JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			int counter = 0;
			while (counter < FORMATO_JSON_FECHA.length) {
				SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_JSON_FECHA[counter]);
				try {
					return sdf.parse(p.getText());
				} catch (ParseException e) {
				}
				counter++;
			}

			return null;
		}
	}

	public static class AccionPantallaDeserializer extends JsonDeserializer<String> {

		@Override
		public String deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String accion = null;
			if (p.getIntValue() == 1)
				accion = "Add";
			else if (p.getIntValue() == 2)
				accion = "Update";
			else if (p.getIntValue() == 4)
				accion = "CopyIntermedio";

			return accion;
		}

	}
}
