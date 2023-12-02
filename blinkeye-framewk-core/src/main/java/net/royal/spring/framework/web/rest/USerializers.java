package net.royal.spring.framework.web.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UString;

public class USerializers {
	private static final String FORMATO_JSON_FECHA_SERIALIZAR = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";// "yyyy-MM-dd'T'HH:mm:ss";
	private static final String FORMATO_JSON_FECHA_LISTA = "dd/MM/yyyy";

	private USerializers() {
	}

	public static class StringFlagSerializer extends JsonSerializer<String> {
		@Override
		public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
			jgen.writeBoolean(UBoolean.validarFlag(value));
		}
	}

	public static class DateSerializer extends JsonSerializer<Date> {
		final SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_JSON_FECHA_SERIALIZAR);

		@Override
		public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			gen.writeString(sdf.format(value));
		}

	}

	public static class DateListaSerializer extends JsonSerializer<Date> {
		final SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_JSON_FECHA_LISTA);

		@Override
		public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
			gen.writeString(sdf.format(value));
		}

	}

	public static class StringSinEspaciosEnBlancoSerializer extends JsonSerializer<String> {
		@Override
		public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
			jgen.writeString(UString.esNuloVacio(value) ? null : value.trim());			
		}
	}
}