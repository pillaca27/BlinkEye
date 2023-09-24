package net.royal.spring.sg.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sg.dominio.BeanSeguridadautorizaciones;
import net.royal.spring.sg.dominio.BeanSeguridadautorizacionesPk;
import net.royal.spring.sg.dominio.dto.DtoComunAutorizacionFuncion;
import net.royal.spring.sg.dominio.dto.DtoComunSeguridadConcepto;
import net.royal.spring.sg.dominio.dto.DtoComunSeguridadTabsMenu;
import net.royal.spring.sg.dominio.dto.DtoComunSeguridadautorizaciones;
import net.royal.spring.sg.dominio.dto.DtoComunUsuario;
import net.royal.spring.sg.dominio.filtro.FiltroComunSeguridadTabsMenu;
import net.royal.spring.sg.dominio.filtro.FiltroComunPaginacionAutorizacionConcepto;
import net.royal.spring.sg.dominio.filtro.FiltroComunPaginacionUsuario;

@Repository
public class SeguridadautorizacionesDaoImpl extends GenericoDaoImpl<BeanSeguridadautorizaciones, BeanSeguridadautorizacionesPk> {

	private static Logger logger = LogManager.getLogger(SeguridadautorizacionesDaoImpl.class);

	public SeguridadautorizacionesDaoImpl() {
		super("seguridadautorizaciones");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSeguridadautorizaciones obtenerPorId(String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) {
		return obtenerPorId(new BeanSeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	public BeanSeguridadautorizaciones coreInsertar(BeanSeguridadautorizaciones bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanSeguridadautorizaciones coreActualizar(BeanSeguridadautorizaciones bean) {
		this.actualizar(bean);
		return bean;
	}
	
	/**
	 * f_sql_read_user_autorizacion
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public String tieneAutorizacion(DtoComunSeguridadautorizaciones pk) throws Exception {
		String resultado="N";
		pk.setEstado("A");
		List<DtoComunSeguridadautorizaciones> lstAutorizacion = listarPorPkYEstado(pk);
		List<DtoComunSeguridadautorizaciones> lstPorPerfil = listarConPerfilPorPkYEstado(pk);
		
		if ((lstAutorizacion.size()>0) || (lstPorPerfil.size()>0)) 
			resultado="S";
		
		if (resultado.equals("N")) {
			pk.setGrupo("SYSTEM");
			pk.setConcepto("SYSADM");
			logger.debug("SYSADM");
			List<DtoComunSeguridadautorizaciones> lstSysAdm = listarPorPkYEstado(pk);
			pk.setGrupo("SYSTEM");
			pk.setConcepto("SECADM");
			logger.debug("SECADM");
			List<DtoComunSeguridadautorizaciones> lstSecAdm = listarPorPkYEstado(pk);
			if ((lstSysAdm.size()>0) || (lstSecAdm.size()>0)) 
				resultado="S";
		}		
		
		logger.debug("resultado:"+resultado);
		return resultado;
	}

	/**
	 * fsqlreaduserautorizacion01
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<DtoComunSeguridadautorizaciones> listarPorPkYEstado(DtoComunSeguridadautorizaciones pk) throws Exception {
		logger.debug("listarPorPkYEstado");
		if (UString.esNuloVacio(pk.getEstado()))
			pk.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, pk.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, pk.getConcepto()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pk.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, pk.getEstado()));
		List datos = this.listarPorQuery(DtoComunSeguridadautorizaciones.class, "seguridadautorizaciones.listarPorPkYEstado",parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	/**
	 * fsqlreaduserautorizacion02
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<DtoComunSeguridadautorizaciones> listarConPerfilPorPkYEstado(DtoComunSeguridadautorizaciones pk) throws Exception {
		logger.debug("listarConPerfilPorPkYEstado");
		if (UString.esNuloVacio(pk.getEstado()))
			pk.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, pk.getGrupo()));
		parametros.add(new DominioParametroPersistencia("p_concepto", String.class, pk.getConcepto()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pk.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, pk.getEstado()));
		List datos = this.listarPorQuery(DtoComunSeguridadautorizaciones.class, "seguridadautorizaciones.listarConPerfilPorPkYEstado",parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	
	//LEONARDO MANTENIMIENTO SEGURIDAD 
		public BeanSeguridadautorizaciones coreRegistrarOactualizar(BeanSeguridadautorizaciones bean) {
			this.registrarOactualizar(bean);
			return bean;
		}
	
		public DtoComunSeguridadTabsMenu obtenerSeguridadTabsMenu(FiltroComunSeguridadTabsMenu filtro, boolean retornarVacio) {
			List<DominioParametroPersistencia> parameters = new ArrayList<DominioParametroPersistencia>();
			parameters.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getPerfil()));
			if(contar("seguridadautorizaciones.esSUPERUSER", parameters)==1) {
				DtoComunSeguridadTabsMenu admin = new DtoComunSeguridadTabsMenu();
				admin.setFlgAgregar("S");
				admin.setFlgAprobar("S");
				admin.setFlgBorrar("S");
				admin.setFlgEnviarCorreo("S");
				admin.setFlgModificar("S");
				admin.setFlgOtros("S");
				admin.setFlgVerDocs("S");
				admin.setConcepto(filtro.getConcepto());
				admin.setDescripcion(filtro.getConcepto());
				return admin;
			}
			else {
				List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
				parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacion()));
				parametros.add(new DominioParametroPersistencia("p_concepto", String.class, filtro.getConcepto()));
				parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getPerfil()));
				parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));

				List<?> datos2 = listarPorQuery(DtoComunSeguridadTabsMenu.class, "seguridadautorizaciones.obtenerSeguridadTabsMenu", parametros);
				if (datos2.size()>0) {
					return (DtoComunSeguridadTabsMenu)datos2.get(0);
				}else {
					if(retornarVacio) {
						return null;
					}
					return new DtoComunSeguridadTabsMenu();
				}
				/*SeguridadTabsMenu datos = (SeguridadTabsMenu) obtenerPorQuery(
						"seguridadautorizaciones.obtenerSeguridadTabsMenu", parametros, SeguridadTabsMenu.class);*/
				
			}
		}
		
		
	public DominioPaginacion listarFunciones(FiltroComunPaginacionAutorizacionConcepto filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getCodigoAplicacion())) {
			filtro.setCodigoAplicacion(null);
		}

		if (UString.estaVacio(filtro.getUsuario())) {
			filtro.setCodigoAplicacion(null);
		}
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getCodigoAplicacion()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));
		Integer registros = contar("seguridadautorizaciones.ContarFunciones", parametros);
//		List<?> datos = listarPorQuery(DtoAutorizacionFuncion.class, "seguridadautorizaciones.ListarFunciones", parametros);
//		
//		
		List<?> datos = listarConPaginacion(filtro.getPaginacion(), parametros, "seguridadautorizaciones.ListarFunciones", DtoComunAutorizacionFuncion.class);

		filtro.paginacion.setPaginacionRegistrosEncontrados(registros);
		filtro.paginacion.setPaginacionListaResultado(datos);
		return filtro.paginacion;

	}
	
	
	public DominioPaginacion listarConceptos(FiltroComunPaginacionAutorizacionConcepto filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getCodigoAplicacion())) {
			filtro.setCodigoAplicacion(null);
		}

		if (UString.estaVacio(filtro.getUsuario())) {
			filtro.setCodigoAplicacion(null);
		}

		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getCodigoAplicacion()));
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));
		Integer registros = contar("seguridadautorizaciones.ContarConceptos", parametros);

		List datos = listarConPaginacion(filtro.getPaginacion(), parametros, "seguridadautorizaciones.ListarConceptos", DtoComunSeguridadConcepto.class);

		filtro.paginacion.setPaginacionRegistrosEncontrados(registros);
		filtro.paginacion.setPaginacionListaResultado(datos);
		return filtro.paginacion;
	}
	
	public DominioPaginacion listarreportesseguridad(FiltroComunPaginacionAutorizacionConcepto filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(filtro.getCodigoAplicacion())) {
			filtro.setCodigoAplicacion(null);
		}

		if (UString.estaVacio(filtro.getUsuario())) {
			filtro.setCodigoAplicacion(null);
		}
		String Empresa = null;
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getCodigoAplicacion()));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		//parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getGrupo()));
		parametros.add(new DominioParametroPersistencia("empresa", String.class, Empresa));
		
		Integer registros = contar("seguridadautorizaciones.ListarReportesSeguridadCount", parametros);
		List<?> datos = listarConPaginacion(filtro.getPaginacion(), parametros,
				"seguridadautorizaciones.ListarReportesSeguridad", DtoComunAutorizacionFuncion.class);
		filtro.paginacion.setPaginacionRegistrosEncontrados(registros);
		filtro.paginacion.setPaginacionListaResultado(datos);
		return filtro.paginacion;

	}

	//SEGURIDAD KPI INDICADORES 
	
	public DominioPaginacion listarUsuariospaginado(SeguridadUsuarioActual usuarioActual,
			FiltroComunPaginacionUsuario filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		Integer registros = contar("seguridadautorizaciones.listarUsuariospaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "seguridadautorizaciones.listarUsuariospaginadoSentencia",
				DtoComunUsuario.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	//FIN
}
