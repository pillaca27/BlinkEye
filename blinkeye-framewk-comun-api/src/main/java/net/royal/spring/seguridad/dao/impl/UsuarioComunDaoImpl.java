package net.royal.spring.seguridad.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.seguridad.dominio.BeanUsuario;
import net.royal.spring.seguridad.dominio.BeanUsuarioPk;
import net.royal.spring.seguridad.dominio.dto.DtoComunSegAutorizacion;
import net.royal.spring.seguridad.dominio.dto.DtoTablaBigDecimal;
import net.royal.spring.seguridad.dominio.dto.DtoComunUsuario02;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionUsuario;

@Repository
public class UsuarioComunDaoImpl extends GenericoDaoImpl<BeanUsuario, BeanUsuarioPk> {

	private static Logger logger = LogManager.getLogger(BeanUsuario.class);

	public UsuarioComunDaoImpl() {
		super("usuario");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanUsuario obtenerPorId(String pusuario) {
		return obtenerPorId(new BeanUsuarioPk(pusuario));
	}

	public BeanUsuario coreInsertar(BeanUsuario bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanUsuario coreInsertar(SeguridadUsuarioActual usuarioActual, BeanUsuario bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanUsuario coreActualizar(SeguridadUsuarioActual usuarioActual, BeanUsuario bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanUsuario coreActualizar(BeanUsuario bean) {
		this.actualizar(bean);
		return bean;
	}

 
	
	//LEONARDO DAO SEGURIDAD MANTENIMIENTO
	
	public DominioPaginacion listar(FiltroComunPaginacionUsuario filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getUsuarioPerfil())) {
			filtro.setUsuarioPerfil(null);
		}
		if (UString.estaVacio(filtro.getNombre())) {
			filtro.setNombre(null);
		}
		if (UString.estaVacio(filtro.getCodigoUsuario())) {
			filtro.setCodigoUsuario(null);
		}
		if (UString.estaVacio(filtro.getEstado())) {
			filtro.setEstado(null);
		}

		parametros.add(new DominioParametroPersistencia("p_tipoUsuario", String.class, filtro.getUsuarioPerfil()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_codigoUsuario", String.class, filtro.getCodigoUsuario()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("usuario.contar", parametros);

		registros = registros == null ? 0 : registros;
		List<?> datos = listarConPaginacion(filtro.getPaginacion(), parametros, "usuario.listarusuario", DtoComunUsuario02.class);
		filtro.paginacion.setPaginacionRegistrosEncontrados(registros);
		filtro.paginacion.setPaginacionListaResultado(datos);
		return filtro.paginacion;

	}
	
	public DtoTablaBigDecimal obtenerCorreo(String usuario) {
		logger.debug("usuario:" + usuario);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigoUsuario", String.class, usuario));
		List<?> datos = listarPorQuery(DtoTablaBigDecimal.class, "usuario.obtenerCorreo", parametros);
		logger.debug("usuario:" + datos.size());
		DtoTablaBigDecimal dto = (DtoTablaBigDecimal) datos.get(0);
		return dto;
	}
	
	//FIN LEONARDO
	
	
	public List<DtoComunSegAutorizacion> listarDtoAutorizacionesAplicacionesUsuarioPerfilAdministrador(String usuario) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		logger.debug(usuario);
		parametros.add(new DominioParametroPersistencia("p_usuarioid", String.class, usuario));
		List datos = this.listarPorQuery(DtoComunSegAutorizacion.class, "usuario.listarDtoAutorizacionesAplicacionesUsuarioPerfilAdministrador", parametros);
		return datos;
	}
	
}
