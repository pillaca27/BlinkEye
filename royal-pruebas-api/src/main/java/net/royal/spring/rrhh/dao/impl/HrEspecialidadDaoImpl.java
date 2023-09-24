package net.royal.spring.rrhh.dao.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidadPk;
import net.royal.spring.rrhh.dominio.dto.DtoHrCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrEspecialidad;

@Repository
public class HrEspecialidadDaoImpl extends GenericoDaoImpl<BeanHrEspecialidad, BeanHrEspecialidadPk>{
	
	public HrEspecialidadDaoImpl(){
		super("hrespecialidad");
	}
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory){
		super.setSessionFactory(factory);
	}
	
	public List<DtoHrEspecialidad> listarEspecialidades(FiltroHrEspecialidad filtro) throws Exception{
		
//		if (UString.estaVacio(filtro.getEstado()))
//			filtro.setEstado(null);
//		if (UInteger.esCeroOrNulo(filtro.getEspecialidad()))
//			filtro.setEspecialidad(0);
		
		List datos = this.listarPorQuery(DtoHrEspecialidad.class, "hrespecialidad.listar");
		
		return datos;
	}

	public String generarCodigo() {
		
		List lst = listarPorQuery(DtoTabla.class, "hrespecialidad.generarCodigo");
		if (lst.size() == 0) {
			return null;
		}
		return ((DtoTabla) (lst.get(0))).getCodigo();
	}

	public DtoHrEspecialidad obtenerDto(Integer especialidad) {
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_especialidad", Integer.class, especialidad));
	
		List lst = listarPorQuery(DtoHrEspecialidad.class, "hrespecialidad.obtenerDto", parametros);
		if (lst.size() == 0) {
			return null;
		}
		return ((DtoHrEspecialidad) (lst.get(0)));
	}
	
	
}
