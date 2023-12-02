package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.core.dominio.dto.DtoComunAccountmst;
import net.royal.spring.core.dominio.dto.DtoComunAfemst;
import net.royal.spring.core.dominio.dto.DtoComunDwMaAccount;
import net.royal.spring.core.dominio.filtro.FiltroComunAccountmst;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/accountmstcomun")
@CrossOrigin(origins = "*")
public class AccountmstComunRest  extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AccountmstComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AccountmstComunRest() {
		super("accountmst");
	}
	
	@ApiOperation(notes = "Listar dto tabla. Retorno: List DtoTabla",					
			nickname="AC_COUNTMST_CLIST", value = "Listar dto", tags = {"AC_COUNTMST", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listar() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "accountmst.listar");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto tabla activos. Retorno: List DtoTabla",					
			nickname="AC_COUNTMST_CLISTACT", value = "Listar dto", tags = {"AC_COUNTMST", "LISTAR"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)	        
	public ResponseEntity<List<DtoTabla>> listarActivos() {	        	            
		logger.debug("listar paises");
		List datos = this.listarPorQuery(DtoTabla.class, "accountmst.listarActivos");
		return new ResponseEntity<List<DtoTabla>>(datos, HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="AC_COUNTMST_COBT", value = "Listar dto", tags = {"AC_COUNTMST", "OBTENER"})
	@Transactional
	@PutMapping(value = "/obtenertabla", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoTabla> obtenertabla(@RequestBody DtoTabla filtro) { 
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
        parametros.add(new DominioParametroPersistencia("p_account", String.class, filtro.getCodigo()));
        List datos = this.listarPorQuery(DtoTabla.class, "accountmst.obtenertabla", parametros);
        DtoTabla dto = null;
        if (datos.size()==1)
        	dto=(DtoTabla)datos.get(0);
		return new ResponseEntity<DtoTabla>(dto, HttpStatus.OK); 
	}
	
	@ApiOperation(notes = "Obtener tabla. Entrada: DtoTabla: codigo. Retorno: DtoTabla",					
			nickname="AC_COUNTMST_COBT", value = "Listar dto", tags = {"AC_COUNTMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenercommitmentvalidation", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerCommitmentvalidation(@RequestBody DtoComunAccountmst pk) throws Exception {
		logger.debug("obtenercommitmentvalidation");
		DtoComunAccountmst dto = obtenerdtoCore(pk);
		String res = "N";
		if (dto.getTransaccionEstado().equals(DominioTransaccion.OK))
			res = dto.getCommitmentvalidation();
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto. Entrada: DtoComunAccountmst. Retorno: DtoComunAccountmst",					
			nickname="AC_COUNTMST_COBTDTO", value = "Obtener dto", tags = {"AC_COUNTMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunAccountmst> obtenerdto(@RequestBody DtoComunAccountmst pk) throws Exception {
		logger.debug("obtenerdto");
		DtoComunAccountmst dto = obtenerdtoCore(pk);
		return new ResponseEntity<DtoComunAccountmst>(dto,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto localname. Entrada: DtoComunAccountmst. Retorno: String",					
			nickname="AC_COUNTMST_COBTNAME", value = "Obtener dto", tags = {"AC_COUNTMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerlocalname", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerLocalname(@RequestBody DtoComunAccountmst pk) throws Exception {
		logger.debug("obtenerlocalname");
		DtoComunAccountmst dto = obtenerdtoCore(pk);
		return new ResponseEntity<String>(dto.getLocalname(),HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener dto localname. Entrada: DtoComunAccountmst. Retorno: String",					
			nickname="AC_COUNTMST_COBTNAME", value = "Obtener dto", tags = {"AC_COUNTMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerbudgetcategorydefault", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerBudgetcategorydefault(@RequestBody DtoComunAccountmst pk) throws Exception {
		logger.debug("obtenerbudgetcategorydefault");
		DtoComunAccountmst dto = obtenerdtoCore(pk);
		return new ResponseEntity<String>(dto.getBudgetcategorydefault(),HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Obtener budget categorua default 2. Entrada: DtoComunAccountmst. Retorno: String",					
			nickname="AC_COUNTMST_COBTCATDEF", value = "Obtener budget categorua default 2", tags = {"AC_COUNTMST", "OBTENER"})
	@Transactional
	@PutMapping(value="/obtenerbudgetcategorydefault2", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> obtenerBudgetcategorydefault2(@RequestBody DtoComunAccountmst pk) throws Exception {
		logger.debug("obtenerbudgetcategorydefault2");
		DtoComunAccountmst dto = obtenerdtoCore(pk);
		return new ResponseEntity<String>(dto.getBudgetcategorydefault2(),HttpStatus.OK);
	}
	
	public DtoComunAccountmst obtenerdtoCore(DtoComunAccountmst pk) throws Exception {
		logger.debug("obtenerdtoCore");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_account", String.class, pk.getAccount()));
		List datos = this.listarPorQuery(DtoComunAccountmst.class, "accountmst.obtenerdto",parametros);
		DtoComunAccountmst dto;
		if (datos.size()==1) {
			dto = (DtoComunAccountmst)datos.get(0);
			dto.setTransaccionEstado(DominioTransaccion.OK);
		}else {
			dto = new DtoComunAccountmst();
		    dto.setTransaccionEstado(DominioTransaccion.ERROR);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		}
		return dto;
	}
	
	@ApiOperation(notes = "Listar dto filtros. Entrada: DtoComunAccountmst. Retorno: List DtoComunAccountmst",					
			nickname="AC_COUNTMST_CLISTFIL", value = "Obtener budget categorua default 2", tags = {"AC_COUNTMST", "LISTAR"})
	@Transactional
	@PutMapping(value="/listardtofiltros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAccountmst>> listardtofiltros(@RequestBody DtoComunAccountmst filtro) throws Exception {
		logger.debug("listardtofiltros");
		List datos = listardtocore(filtro);
		return new ResponseEntity<List<DtoComunAccountmst>>(datos,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto por account. Entrada: DtoComunAccountmst. Retorno: List DtoComunAccountmst",					
			nickname="AC_COUNTMST_CLISTACC", value = "Listar dto por account", tags = {"AC_COUNTMST", "LISTAR"})
	@Transactional
	@PutMapping(value="/listardtoporaccount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAccountmst>> listarDtoPorAccount(@RequestBody DtoComunAccountmst filtro) throws Exception {
		logger.debug("listardtoporaccount");
		List datos = listardtocore(filtro);
		return new ResponseEntity<List<DtoComunAccountmst>>(datos,HttpStatus.OK);
	}
	
	@ApiOperation(notes = "Listar dto por account plan contable. Entrada: DtoComunAccountmst. Retorno: List DtoComunAccountmst",					
			nickname="AC_COUNTMST_CLISTACC", value = "Listar dto por account plan contable", tags = {"AC_COUNTMST", "LISTAR"})
	@Transactional
	@PutMapping(value="/listardtoporaccountplancontable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunAccountmst>> listarDtoPorAccountPlancontable(@RequestBody DtoComunAccountmst filtro) throws Exception {
		logger.debug("listardtoporaccountplancontable");
		List datos = listardtocore(filtro);
		return new ResponseEntity<List<DtoComunAccountmst>>(datos,HttpStatus.OK);
	}
	
	public List<DtoComunAccountmst> listardtocore(DtoComunAccountmst filtro) throws Exception {
		if (UString.esNuloVacio(filtro.getAccount()))
			filtro.setAccount(null);
		if (UString.esNuloVacio(filtro.getLocalname()))
			filtro.setLocalname(null);
		else
			filtro.setLocalname(filtro.getLocalname().toUpperCase());
		if (UString.esNuloVacio(filtro.getPlancontable()))
			filtro.setPlancontable(null);
		if (UString.esNuloVacio(filtro.getStatus()))
			filtro.setStatus(null);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_account", String.class, filtro.getAccount()));
		parametros.add(new DominioParametroPersistencia("p_localname", String.class, filtro.getLocalname()));
		parametros.add(new DominioParametroPersistencia("p_status", String.class, filtro.getStatus()));
		List datos = this.listarPorQuery(DtoComunAccountmst.class, "accountmst.listardtofiltros",parametros);
		return datos;
	}
	
	
	
	@ApiOperation(notes = "Listar paginado. Entrada: FiltroComunAccountmst. Retorno: List DtoComunDwMaAccount",					
			nickname="AC_COUNTMST_CLISTPAG", value = "Listar paginado", tags = {"AC_COUNTMST", "LISTAR"})
	@Transactional
	@PutMapping(value = "/listarpaginado",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunDwMaAccount>> listarPaginado(@RequestBody FiltroComunAccountmst fil)
    {
		logger.debug("listar cuenta contable filtro paginado");
		List<DtoComunDwMaAccount> lista = new ArrayList<DtoComunDwMaAccount>();
		
		String w_prime;
		String w_element;
		String w_plancontable;
		String w_buscar;
		String codigoDescripcionFlag;

		if (UString.esNuloVacio(fil.getPrime())) {
			w_prime = null;
		} else {
			w_prime = fil.getPrime();
		}
		if (UString.esNuloVacio(fil.getElement())) {
			w_element = null;
		} else {
			w_element = fil.getElement();
		}
		w_plancontable = fil.getPlancontable();

		w_buscar = fil.getAccount();
		codigoDescripcionFlag = fil.getCodigodescripcionflag();

		if (UValidador.estaVacio(w_buscar) || UValidador.esNulo(w_buscar)) {
			w_buscar = " ";
		}

		String dato = w_buscar;
		String[] obj = new String[dato.length()];
		for (int i = 0; i < dato.length(); i++) {
			obj[i] = dato.substring(i, i + 1);
			if (obj[i].equals("'")) {
				w_buscar = "";
			}
		}

		List<DominioParametroPersistencia> filtro = new ArrayList<DominioParametroPersistencia>();
		filtro.add(new DominioParametroPersistencia("status", String.class, "A"));

		StringBuilder sb = new StringBuilder();
		sb.append(this.obtenerSentenciaSqlPorQuery("accountmst.listarCuenta"));

		if(codigoDescripcionFlag == null) {
			codigoDescripcionFlag = "C";
		}
		
		if (codigoDescripcionFlag.equals("C")) {
			if (UString.esNuloVacio(w_buscar) || w_buscar.trim().equals("")) {
				w_buscar = "";
			} else {
				sb.append(" AND (accountmst.account >= '" + w_buscar + "' ) ");
			}
		}
		if (codigoDescripcionFlag.equals("D")) {
			w_buscar = "%" + w_buscar.toUpperCase() + "%";
			if (UString.esNuloVacio(w_buscar)) {
				w_buscar = "";
				sb.append(" AND (Upper(accountmst.localname) >= " + w_buscar + " ) ");
			} else {
				sb.append(" AND (Upper(accountmst.localname) like '" + w_buscar + "')");
			}
		}

		if (!UString.esNuloVacio(w_prime)) {
			sb.append(" AND accountmst.prime LIKE '" + w_prime + "'");
		}
		if (!UString.esNuloVacio(w_element)) {
			sb.append(" AND accountmst.element like '" + w_element + "'");
		}
		if (!UString.esNuloVacio(w_plancontable)) {
			sb.append(" AND accountmst.plancontable like '" + w_plancontable + "'");
		}
		sb.append(" ORDER BY accountmst.ACCOUNT ");
		List datos;
		datos = this.listarPorSentenciaSQL(sb, filtro, DtoComunDwMaAccount.class);
		
		lista.addAll(datos);		

		if (UValidador.esListaVacia(lista)) {
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getMulticompanyflag().equals("Y")) {
					lista.get(i).setCompanyowner(this.getUsuarioActual().getCompaniaCodigo());
				}
			}
		}
		
        
		return new ResponseEntity<List<DtoComunDwMaAccount>>(lista, HttpStatus.OK);
    }
	

}
