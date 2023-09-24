package net.royal.spring.rrhh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrEmpleadoCapacitacion;
import net.royal.spring.rrhh.dominio.BeanHrEmpleadoCapacitacionPk;
import net.royal.spring.rrhh.dominio.dto.DtoHrCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrEmpleadoCapacitacion;

@Repository
public class HrEmpleadoCapacitacionDaoImpl
		extends GenericoDaoImpl<BeanHrEmpleadoCapacitacion, BeanHrEmpleadoCapacitacionPk> {

	public HrEmpleadoCapacitacionDaoImpl() {
		super("hrempleadocapacitacion");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoHrEmpleadoCapacitacion> obtenerListaDto(String companyOwner, String capacitacion) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, companyOwner));
		parametros.add(new DominioParametroPersistencia("p_capacitacion", String.class, capacitacion));
		List lst = listarPorQuery(DtoHrEmpleadoCapacitacion.class, "hrempleadocapacitacion.obtenerListaDto",
				parametros);
		return lst;
	}

	public List<DtoHrEmpleadoCapacitacion> listarParticipantes(DtoHrCapacitacion dto) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, dto.getCompanyOwner()));
		parametros.add(new DominioParametroPersistencia("p_capacitacion", String.class, dto.getCapacitacion()));
		List lst = listarPorQuery(DtoHrEmpleadoCapacitacion.class, "hrempleadocapacitacion.listarParticipantes", parametros);
		return lst;
	}

}
