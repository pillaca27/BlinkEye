package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.BeanDepartamento;
import net.royal.spring.core.dominio.BeanDepartamentoPk;
import net.royal.spring.core.dominio.dto.DtoComunDepartamento;
import net.royal.spring.core.dominio.dto.DtoComunProvincia;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.dominio.filtro.FiltroComunDepartamento;
import net.royal.spring.core.dominio.lista.DtlComunDepartamento;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class DepartamentoDaoImpl extends GenericoDaoImpl<BeanDepartamento, BeanDepartamentoPk> {

	private static Logger logger = LogManager.getLogger(BeanDepartamento.class);

	public DepartamentoDaoImpl() {
		super("departamento");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanDepartamento obtenerPorId(String pdepartamento) {
		return obtenerPorId(new BeanDepartamentoPk( pdepartamento));
	}

	public BeanDepartamento coreInsertar(BeanDepartamento bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanDepartamento coreActualizar(BeanDepartamento bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunDepartamento obtenerDtoCore(DtoComunDepartamento pk) throws Exception {
		DtoComunDepartamento dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunDepartamento();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunDepartamento> listarDtoCore(DtoComunDepartamento filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Departamento.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));

		List datos = this.listarPorQuery(DtoComunDepartamento.class, "departamento.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunDepartamento obtenerDto(DtoComunDepartamento pk) {
		DtoComunDepartamento dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, pk.getDepartamento()));

		List lst = listarPorQuery(DtoComunDepartamento.class, "departamento.obtenerDto", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunDepartamento) lst.get(0);
			
			if(pk.getAccion().equals("PROV")) {
				List lstProvincias = listarPorQuery(DtoComunProvincia.class, "departamento.obtenerDtoProvincia", parametros);
				if(lstProvincias.size() > 0) {
					dto.setProvincias(lstProvincias);
				}
			}
			else if(pk.getAccion().equals("DISTR")) {
				List<DominioParametroPersistencia> parametrosDetalle = new ArrayList<DominioParametroPersistencia>();
				parametrosDetalle.add(new DominioParametroPersistencia("p_departamento", String.class, pk.getDepartamento()));
				parametrosDetalle.add(new DominioParametroPersistencia("p_provincia", String.class, pk.getPais()));
				List lstDistritos = listarPorQuery(DtoComunZonapostal.class, "departamento.obtenerDtoZonaPostal", parametrosDetalle);
				
				if(lstDistritos.size() > 0) {
					dto.setDistritos(lstDistritos);
				}
			}
			else if(pk.getAccion().equals("DELETE")) {
				List lstProvincias = listarPorQuery(DtoComunProvincia.class, "departamento.obtenerDtoProvincia", parametros);
				if(lstProvincias.size() > 0) {
					dto.setProvincias(lstProvincias);
					
					for (DtoComunProvincia provincias : dto.getProvincias()) {
						List<DominioParametroPersistencia> parametrosDetalle = new ArrayList<DominioParametroPersistencia>();
						parametrosDetalle.add(new DominioParametroPersistencia("p_departamento", String.class, pk.getDepartamento()));
						parametrosDetalle.add(new DominioParametroPersistencia("p_provincia", String.class, provincias.getProvincia()));
						List lstDistritos = listarPorQuery(DtoComunZonapostal.class, "departamento.obtenerDtoZonaPostal", parametrosDetalle);
						
						if(lstDistritos.size() > 0) {
							provincias.setZonaPostal(lstDistritos);
						}
						
					}

				}
			}
			
		}
			
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunDepartamento filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getDepartamento()))
			filtro.setDepartamento(null);
		// TODO Departamento.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));

		Integer registros = contar("departamento.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "departamento.listarPaginadoSentencia",DtlComunDepartamento.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarDepartamentosGeograficos(FiltroComunDepartamento filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getDepartamento()))
			filtro.setDepartamento(null);
		// TODO Departamento.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_departamento", String.class, filtro.getDepartamento()));

		List lst = listarPorQuery(DtlComunDepartamento.class, "departamento.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
