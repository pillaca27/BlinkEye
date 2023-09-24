package net.royal.spring.rrhh.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad2;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad2Pk;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad2;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrEspecialidad2;

@Repository
public class HrEspecialidad2DaoImpl extends GenericoDaoImpl<BeanHrEspecialidad2, BeanHrEspecialidad2Pk>{
	
	public HrEspecialidad2DaoImpl() {
		super("hrespecialidad2");
	}
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	
	public BeanHrEspecialidad2 obtenerPorId(Integer especialidad) {
		return obtenerPorId(new BeanHrEspecialidad2Pk(especialidad));
	}
	
	public String generarCodigo() {
		
		List lst = listarPorQuery(DtoTabla.class, "hrespecialidad2.generarCodigo");
		if (lst.size() == 0) {
			return null;
		}
		return ((DtoTabla) (lst.get(0))).getCodigo();
	}
	
	public BeanHrEspecialidad2 coreInsertar(BeanHrEspecialidad2 bean) {
		this.registrar(bean);
		return bean;
	}
	
	public BeanHrEspecialidad2 coreInsertar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean, String estado) {
		if (UString.estaVacio(estado))
			estado = "A";
		bean.setEstado(estado);
		bean.setUltimoUsuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}
	
	public BeanHrEspecialidad2 coreActualizar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean, String estado) {
		if (UString.estaVacio(estado))
			estado = "A";
		bean.setEstado(estado);
		bean.setUltimoUsuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}
	
	@Transactional
	public BeanHrEspecialidad2 coreActualizar(BeanHrEspecialidad2 bean) {
		this.actualizar(bean);
		return bean;
	}
	
	public DominioPaginacion listarPaginacion(FiltroHrEspecialidad2 filtros) throws Exception{
		
		//Limpiar Filtros
		if (UInteger.esCeroOrNulo(filtros.getEspecialidad())) {
			filtros.setEspecialidad(null);
		}
		
		if (UInteger.esCeroOrNulo(filtros.getEmpleadoSolicitante())) {
			filtros.setEmpleadoSolicitante(null);
		}
		
		if (UString.estaVacio(filtros.getDescripcion())) {
			filtros.setDescripcion(null);
		}
		
		if (UString.estaVacio(filtros.getEstado())) {
			filtros.setEstado(null);
		}
		
		//Agregar Parametros
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_especialidad", Integer.class, filtros.getEspecialidad()));
		parametros.add(new DominioParametroPersistencia("p_empleadoSolicitante", Integer.class, filtros.getEmpleadoSolicitante()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtros.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtros.getEstado()));
		
		//Contar
		Integer cantidad = contar("hrespecialidad2.contarPaginacion", parametros);
		
		//Listado
		List datos = this.listarPorQuery(DtoHrEspecialidad2.class, "hrespecialidad2.listarparametros", parametros);
		
		//Enviar Resultados
		filtros.getDominioPaginacion().setPaginacionRegistrosEncontrados(cantidad);
		filtros.getDominioPaginacion().setPaginacionListaResultado(datos);
		
		return filtros.getDominioPaginacion();
	}

	public DtoHrEspecialidad2 obtenerDto(Integer especialidad) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_especialidad", Integer.class, especialidad));
	
		List lst = listarPorQuery(DtoHrEspecialidad2.class, "hrespecialidad2.obtenerDto", parametros);
		if (lst.size() == 0) {
			return null;
		}
		return ((DtoHrEspecialidad2) (lst.get(0)));
	}

}
