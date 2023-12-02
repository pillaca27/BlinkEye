package net.royal.spring.rrhh.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dominio.dto.DtoComunTablaBigDecimal;
import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.rrhh.dominio.dto.DtoComunPersonalReclutamientoSelector;
import net.royal.spring.rrhh.dominio.dto.DtoComunPostulanteSelector;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunPersonalReclutamiento;
import net.royal.spring.rrhh.dominio.filtro.FiltroComunPostulanteSelector;

@RestController
@RequestMapping("/spring/rrhh/hrrequerimientocomun")
@CrossOrigin(origins = "*")
public class HrRequerimientoComunRest extends GenericoHibernateRest {
	
	private static Logger logger = LogManager.getLogger(HrRequerimientoComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	
	public HrRequerimientoComunRest() {
		super("hrrequerimiento");
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/listarPostulantes", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarPostulantes(@RequestBody FiltroComunPostulanteSelector filtro) {
		if (UString.estaVacio(filtro.getNombre())) {
			filtro.setNombre(null);
		}
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_postulante", Integer.class, filtro.getPostulante()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		Integer cantidadEncontrados = this.contar("hrrequerimiento.postulantesContar", parametros);

		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"hrrequerimiento.postulantesListar", DtoComunPostulanteSelector.class);

		filtro.getPaginacion().setPaginacionListaResultado(lista);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}

	@Transactional(readOnly = true)
	@PostMapping(value = "/listarPersonalReclutamiento", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarPersonalReclutamiento(@RequestBody FiltroComunPersonalReclutamiento filtro) {
		if (UString.estaVacio(filtro.getNombre())) {
			filtro.setNombre(null);
		}
		if (UString.estaVacio(filtro.getCompania())) {
			filtro.setCompania(null);
		}
		if (UString.estaVacio(filtro.getTipoPlanilla())) {
			filtro.setTipoPlanilla(null);
		}
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, filtro.getEmpleado()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompania()));
		parametros.add(new DominioParametroPersistencia("p_tipoplanilla", String.class, filtro.getTipoPlanilla()));
		Integer cantidadEncontrados = this.contar("hrrequerimiento.personalReclutamientoContar",
				parametros);

		List lista = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"hrrequerimiento.personalReclutamientoListar", DtoComunPersonalReclutamientoSelector.class);

		filtro.getPaginacion().setPaginacionListaResultado(lista);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}

}
