package net.royal.spring.logistica.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.logistica.dominio.dto.DtoComunWhDocumentoflujomst;

@RestController
@RequestMapping("/spring/logistica/whdocumentoflujomstcomun")
@CrossOrigin(origins = "*")
public class WhDocumentoflujomstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WhDocumentoflujomstComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WhDocumentoflujomstComunRest() {
		super("whdocumentoflujomst");
	}
		
	@ApiOperation(notes = "Obtener dto contrato proveedor. Entrada: DtoComunWhContratoProveedor: idEmpleado, NumeroContrato, Compania. Retorno: v",					
			nickname="WH_CONTRATOPROVEEDOR-COBT", value = "Obtener dto contrato proveedor", tags = {"WH_CONTRATOPROVEEDOR", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunWhDocumentoflujomst> obtenerDto(@RequestBody DtoComunWhDocumentoflujomst pk) throws Exception {
		logger.debug("obtenerdto");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, pk.getTipodocumento()));
		parametros.add(new DominioParametroPersistencia("p_flujocodigo", String.class, pk.getFlujocodigo()));
		List datos = this.listarPorQuery(DtoComunWhDocumentoflujomst.class, "whdocumentoflujomst.obtenerDto",parametros);
		DtoComunWhDocumentoflujomst dto;
		if (datos.size()==1) {
			dto = (DtoComunWhDocumentoflujomst)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunWhDocumentoflujomst();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return new ResponseEntity<DtoComunWhDocumentoflujomst>(dto,HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhDocumentoflujomst>> listarDtoFiltros(@RequestBody DtoComunWhDocumentoflujomst filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getTipodocumento()))
			filtro.setTipodocumento(null);
		if (UString.esNuloVacio(filtro.getFlujocodigo()))
			filtro.setFlujocodigo(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
        parametros.add(new DominioParametroPersistencia("p_flujocodigo", String.class, filtro.getFlujocodigo()));
        parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
        parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
        List datos = this.listarPorQuery(DtoComunWhDocumentoflujomst.class, "whdocumentoflujomst.listardtofiltros", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhDocumentoflujomst>>(datos, HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/listardtoportipodocumento", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunWhDocumentoflujomst>> listardtoportipodocumento(@RequestBody DtoComunWhDocumentoflujomst filtro) {
		logger.debug("listardtofiltros");
		if (UString.esNuloVacio(filtro.getTipodocumento()))
			filtro.setTipodocumento(null);		
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		else
			filtro.setDescripcionlocal(filtro.getDescripcionlocal().toUpperCase());
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
        List datos = this.listarPorQuery(DtoComunWhDocumentoflujomst.class, "whdocumentoflujomst.listardtoportipodocumento", parametros);
        logger.debug(datos.size());
		return new ResponseEntity<List<DtoComunWhDocumentoflujomst>>(datos, HttpStatus.OK);
	}
	
}
