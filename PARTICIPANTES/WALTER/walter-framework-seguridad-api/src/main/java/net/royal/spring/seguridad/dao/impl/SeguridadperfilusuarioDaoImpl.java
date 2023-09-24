package net.royal.spring.seguridad.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.seguridad.dominio.Seguridadperfilusuario;
import net.royal.spring.seguridad.dominio.SeguridadperfilusuarioPk;


@Repository
public class SeguridadperfilusuarioDaoImpl extends GenericoDaoImpl<Seguridadperfilusuario, SeguridadperfilusuarioPk> {

	private static Logger logger = LogManager.getLogger(Seguridadperfilusuario.class);

	public SeguridadperfilusuarioDaoImpl() {
		super("seguridadperfilusuario");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Seguridadperfilusuario obtenerPorId(String pperfil,String pusuario) {
		return obtenerPorId(new SeguridadperfilusuarioPk( pperfil, pusuario));
	}

	public Seguridadperfilusuario coreInsertar(Seguridadperfilusuario bean) {
		this.registrar(bean);
		return bean;
	}


	public Seguridadperfilusuario coreActualizar(Seguridadperfilusuario bean) {
		this.actualizar(bean);
		return bean;
	}

	

}
