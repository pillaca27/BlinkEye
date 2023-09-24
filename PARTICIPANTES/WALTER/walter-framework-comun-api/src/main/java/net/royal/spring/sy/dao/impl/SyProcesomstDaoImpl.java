package net.royal.spring.sy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.dto.DtoComunAplicacionesmast;
import net.royal.spring.core.dominio.filtro.FiltroComunAplicacionesmast;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyProcesomst;
import net.royal.spring.sy.dominio.BeanSyProcesomstPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyProcesomst;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyProcesomst;

@Repository
public class SyProcesomstDaoImpl extends GenericoDaoImpl<BeanSyProcesomst, BeanSyProcesomstPk> {

	private static Logger logger = LogManager.getLogger(BeanSyProcesomst.class);

	public SyProcesomstDaoImpl() {
		super("syprocesomst");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyProcesomst obtenerPorId(String paplicacioncodigo,String pprocesocodigo) {
		return obtenerPorId(new BeanSyProcesomstPk( paplicacioncodigo, pprocesocodigo));
	}

	public BeanSyProcesomst coreInsertar(BeanSyProcesomst bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSyProcesomst coreActualizar(BeanSyProcesomst bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSyProcesomst obtenerDtoCore(DtoComunSyProcesomst pk) throws Exception {
		DtoComunSyProcesomst dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunSyProcesomst();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunSyProcesomst> listarDtoCore(DtoComunSyProcesomst filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyProcesomst.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getProcesocodigo()))
			filtro.setProcesocodigo(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_procesocodigo", String.class, filtro.getProcesocodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunSyProcesomst.class, "syprocesomst.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunSyProcesomst obtenerDto(DtoComunSyProcesomst pk) {
		DtoComunSyProcesomst dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, pk.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_procesocodigo", String.class, pk.getProcesocodigo()));

		List lst = listarPorQuery(DtoComunSyProcesomst.class, "syprocesomst.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyProcesomst) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSyProcesomst filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyProcesomst.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getProcesocodigo()))
			filtro.setProcesocodigo(null);
		if (UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_procesocodigo", String.class, filtro.getProcesocodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("syprocesomst.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syprocesomst.listarPaginadoSentencia",DtoComunSyProcesomst.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public List<DtoTabla> listaCortaPorNombre(DtoTabla filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		//parametros.add(new DominioParametroPersistencia("p_sidid", String.class, filtro.getSidId()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "syprocesomst.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

	
	public DominioPaginacion listaraplicacionesmastporfiltro(SeguridadUsuarioActual usuarioActual, FiltroComunAplicacionesmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyProcesomst.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getAplicacioncodigo()))
			filtro.setAplicacioncodigo(null);
		if (UString.esNuloVacio(filtro.getDescripcioncorta()))
			filtro.setDescripcioncorta(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
 

		parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, filtro.getAplicacioncodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcioncorta", String.class, filtro.getDescripcioncorta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("syprocesomst.listaraplicacionesmastporfiltroContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "syprocesomst.listaraplicacionesmastporfiltro",DtoComunAplicacionesmast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
