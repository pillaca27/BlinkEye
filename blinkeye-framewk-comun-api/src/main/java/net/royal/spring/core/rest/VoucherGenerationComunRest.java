package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.contabilidad.dominio.filtro.FiltroComunApVoucherGeneration;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.tesoreria.dominio.lista.DtlComunObligaciones;

@RestController
@RequestMapping("/spring/core/vouchergeneration")
@CrossOrigin(origins = "*")
public class VoucherGenerationComunRest extends GenericoHibernateRest {
	private static Logger logger = LogManager.getLogger(VoucherGenerationComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public VoucherGenerationComunRest() {
		super("vouchergeneration");
	}

	@Transactional
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunApVoucherGeneration filtro)
			throws Exception {
		logger.debug("UnidadesmastRest.listarPaginado");

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_periodo", String.class,
				UString.esPeriodoValido(filtro.getPeriodo())));
		List lst = listarPorQuery(DtlComunObligaciones.class, "vouchergeneration.listarPaginadoSentencia", parametros);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(lst.size());
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		DominioPaginacion paginacion = filtro.getPaginacion();
		return new ResponseEntity<DominioPaginacion>(paginacion, HttpStatus.OK);
	}
}
