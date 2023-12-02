package net.royal.spring.seguridad.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.seguridad.dominio.BeanSeguridadperfilusuario;
import net.royal.spring.seguridad.dominio.BeanSeguridadperfilusuarioPk;
import net.royal.spring.seguridad.dominio.dto.DtoComunSeguridadperfilusuario;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionPerfilUsuario;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class SeguridadperfilusuarioComunDaoImpl extends GenericoDaoImpl<BeanSeguridadperfilusuario, BeanSeguridadperfilusuarioPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeguridadperfilusuarioComunDaoImpl() {
		super("seguridadperfilusuario");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSeguridadperfilusuario obtenerPorId(String pperfil, String pusuario) {
		return obtenerPorId(new BeanSeguridadperfilusuarioPk(pperfil, pusuario));
	}

	public BeanSeguridadperfilusuario coreInsertar(BeanSeguridadperfilusuario bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanSeguridadperfilusuario coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario bean,
			String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanSeguridadperfilusuario coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario bean,
			String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanSeguridadperfilusuario coreActualizar(BeanSeguridadperfilusuario bean) {
		this.actualizar(bean);
		return bean;
	}

	@Transactional(readOnly = true)
	public DominioPaginacion listar(FiltroComunPaginacionPerfilUsuario filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getUsuario())) {
			filtro.setUsuario(null);
		}

		if (filtro.getLabeelbutton() != null) {
			if (filtro.getLabeelbutton().equals("Usuario")) {

				return listaruserbyperfil(filtro);
			}
		}

		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		Integer registros = contar("seguridadperfilusuario.contar", parametros);
		List<?> datos = listarPorQuery(DtoComunSeguridadperfilusuario.class, "seguridadperfilusuario.listar", parametros);
		filtro.paginacion.setPaginacionRegistrosEncontrados(registros);
		filtro.paginacion.setPaginacionListaResultado(datos);
		return filtro.paginacion;
	}

	@Transactional(readOnly = true)
	public DominioPaginacion listaruserbyperfil(FiltroComunPaginacionPerfilUsuario filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getUsuario())) {
			filtro.setUsuario(null);
		}

		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		Integer registros = contar("seguridadperfilusuario.contarlistaruUsuariosByPerifl", parametros);

		registros = registros == null ? 0 : registros;
		List<?> datos = listarConPaginacion(filtro.getPaginacion(), parametros,
				"seguridadperfilusuario.listaruUsuariosByPerifl", DtoComunSeguridadperfilusuario.class);
		filtro.paginacion.setPaginacionRegistrosEncontrados(registros);
		filtro.paginacion.setPaginacionListaResultado(datos);
		return filtro.paginacion;

	}

}
