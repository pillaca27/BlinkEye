package net.royal.spring.workflow.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfFirmaDocumento;
import net.royal.spring.workflow.dominio.WfFirmaDocumentoPk;

@Repository
public class WfFirmaDocumentoDaoImpl extends GenericoDaoImpl<WfFirmaDocumento, WfFirmaDocumentoPk> {

	private static final long serialVersionUID = 1L;

	public WfFirmaDocumentoDaoImpl() {
		super("wffirmadocumento");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

}
