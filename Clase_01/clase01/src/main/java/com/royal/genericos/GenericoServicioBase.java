package com.royal.genericos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.royal.util.DominioMensajeUsuario;
import com.royal.util.DominioMensajeUsuario.tipo_mensaje;
import com.royal.util.UString;

public class GenericoServicioBase {
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String msgCode) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessageBase(msgCode, null, null, locale);
	}

	public String getMessage(String msgCode, String defaultMsg) {
		Locale locale = LocaleContextHolder.getLocale();
		return getMessageBase(msgCode, null, defaultMsg, locale);
	}

	private String getMessageBase(String msgCode, Object[] params, String defaultMsg, Locale locale) {
		try {
			if (locale == null)
				locale = LocaleContextHolder.getLocale();
			//messageSource.
			defaultMsg = UString.estaVacio(defaultMsg) ? msgCode : defaultMsg;
			String msg = messageSource.getMessage(msgCode, params, defaultMsg, locale);
			return UString.estaVacio(msg) ? msgCode : msg;
		} catch (Exception e) {
		}
		return msgCode;
	}

	public DominioMensajeUsuario getMsjUsuarioError(String msgCode) {
		String msg = getMessage(msgCode);
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, msg);
		return msj;
	}

	public DominioMensajeUsuario getMsjUsuarioError(ConstraintViolation cons) {
		String msg = cons.getRootBeanClass().getSimpleName();
		msg = msg + "." + cons.getPropertyPath().toString();
		msg = msg + cons.getMessageTemplate().replace("{javax.validation", "").replace(".message}", "");
		msg = getMessage(msg.toLowerCase());
		DominioMensajeUsuario msj = new DominioMensajeUsuario(tipo_mensaje.ERROR, msg);
		return msj;
	}
	
	public List<DominioMensajeUsuario> setMessageError(List<DominioMensajeUsuario> lst,String msg) {
		if (lst==null)
			lst=new ArrayList<>();
		lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, msg));
		return lst;
	}
}
