package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dominio.BeanPersonamast;
import net.royal.spring.core.dominio.BeanPersonamastPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonacuentabancaria;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonapersonagrupo;
import net.royal.spring.core.dominio.dto.DtoComunPersonaMast;
import net.royal.spring.core.dominio.filtro.FiltroComunPersonamast;
import net.royal.spring.core.dominio.lista.DtlComunPersonamast;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class PersonamastWhDaoImpl extends GenericoDaoImpl<BeanPersonamast, BeanPersonamastPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(BeanPersonamast.class);
	
	@Autowired
	MaPersonapersonagrupoDaoImpl personaGrupoDao;
	
	@Autowired
	MaPersonacuentabancariaDaoImpl personaCuentasBancariasDao;

	public PersonamastWhDaoImpl() {
		super("personamast"); 
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanPersonamast obtenerPorId(Integer ppersona) {
		return obtenerPorId(new BeanPersonamastPk( ppersona));
	}

	public BeanPersonamast coreInsertar(BeanPersonamast bean) {
		bean.getPk().setPersona(generarSecuencia());
		this.registrar(bean);
		return bean;
	}


	public BeanPersonamast coreActualizar(BeanPersonamast bean) {
		this.actualizar(bean);
		return bean;
	}

	public Integer generarSecuencia() {
		Criteria c = this
				.getCriteria()
				.setProjection(
						Projections.projectionList().add(
								Projections.max("pk.persona")));
		Integer sec = this.incrementarInteger(c);
		return sec;
	}
	
	public DtoComunPersonaMast obtenerPersona(BeanPersonamast pk) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		DtoComunPersonaMast data=null;
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, pk.getPk().getPersona()));

		List lst = listarPorQuery(DtoComunPersonaMast.class, "personamast.obtenerPersona", parametros);
		if (lst.size() == 1) {
			data = (DtoComunPersonaMast) lst.get(0);
		}
		
		return data;
	}
	
	public String obtenerDocumentoFiscal(BeanPersonamast pk) {
		String dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		DtoComunPersonaMast data=null;
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, pk.getDocumentofiscal()));

		List lst = listarPorQuery(DtoComunPersonaMast.class, "personamast.obtenerDocFiscal", parametros);
		if (lst.size() == 1) {
			data = (DtoComunPersonaMast) lst.get(0);
			dto= data.getPersona().toString().concat(" - ") +data.getBusqueda();
		}
		
		return dto;
	}
	
	public String obtenerBusqueda(BeanPersonamast pk) {
		String dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		DtoComunPersonaMast data=null;
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, pk.getBusqueda()));

		List lst = listarPorQuery(DtoComunPersonaMast.class, "personamast.obtenerBusqueda", parametros);
		if (lst.size() == 1) {
			data = (DtoComunPersonaMast) lst.get(0);
			dto= data.getPersona().toString().concat(" - ") +data.getBusqueda();
		}
		
		return dto;
	}
	
	public String obtenerDocumento(BeanPersonamast pk) {
		String dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		DtoComunPersonaMast data=null;
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, pk.getDocumento()));

		List lst = listarPorQuery(DtoComunPersonaMast.class, "personamast.obtenerDocumento", parametros);
		if (lst.size() == 1) {
			data = (DtoComunPersonaMast) lst.get(0);
			dto= data.getPersona().toString().concat(" - ") +data.getBusqueda();
		}
		
		return dto;
	}
	
	public String obtenerDocumentoIdentidad(BeanPersonamast pk) {
		String dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		DtoComunPersonaMast data=null;
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, pk.getDocumentoidentidad()));

		List lst = listarPorQuery(DtoComunPersonaMast.class, "personamast.obtenerDocIdentidad", parametros);
		if (lst.size() == 1) {
			data = (DtoComunPersonaMast) lst.get(0);
			dto= data.getPersona().toString().concat(" - ") + data.getBusqueda();
		}

		
		return dto;
	}
	public DtoComunPersonaMast obtenerDtoCore(DtoComunPersonaMast pk) throws Exception {
		DtoComunPersonaMast dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunPersonaMast();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		
		DtoComunMaPersonapersonagrupo dtoGrupo= new DtoComunMaPersonapersonagrupo();
		dtoGrupo.setPersona(pk.getPersona());
		List<DtoComunMaPersonapersonagrupo> lst= personaGrupoDao.obtenerLstDto(dtoGrupo);
		
		if(lst.size()>0) 
			dto.setDtoMaPersonapersonagrupo(lst);
		
		DtoComunMaPersonacuentabancaria dtoCuentasBancarias= new DtoComunMaPersonacuentabancaria();
		dtoCuentasBancarias.setPersona(pk.getPersona());
		List<DtoComunMaPersonacuentabancaria> lstBancos= personaCuentasBancariasDao.obtenerLstDto(dtoCuentasBancarias);
		
		if(lstBancos.size()>0) 
			dto.setDtoPersonacuentabancaria(lstBancos);
		

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunPersonaMast> listarDtoCore(DtoComunPersonaMast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Personamast.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));

		List datos = this.listarPorQuery(DtoComunPersonaMast.class, "personamast.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunPersonaMast obtenerDto(DtoComunPersonaMast pk) {
		DtoComunPersonaMast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, pk.getPersona()));

		List lst = listarPorQuery(DtoComunPersonaMast.class, "personamast.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunPersonaMast) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunPersonamast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		String parametro="";
		String contarParametro="";

		if (UInteger.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda("");
		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento("");
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal("");
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad("");
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		
		if (UInteger.esCeroOrNulo(filtro.getPersona()) &&  UString.esNuloVacio(filtro.getBusqueda()) &&
				UString.esNuloVacio(filtro.getDocumento()) && UString.esNuloVacio(filtro.getDocumentofiscal()) && UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setBusqueda("A");
			
			
		
		// TODO Personamast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getBusqueda()));
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_documentofiscal", String.class, filtro.getDocumentofiscal()));
		parametros.add(new DominioParametroPersistencia("p_documentoidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		if(!UString.esNuloVacio(filtro.getTipodocumento())) {
						
			if(filtro.getTipodocumento().equals("S")) {
				parametro="personamast.listarPaginadoSentenciaP";
				contarParametro="personamast.listarPaginadoContarP";
			}else if(filtro.getTipodocumento().equals("E")) {
				parametro="personamast.listarPaginadoSentenciaE";
				contarParametro="personamast.listarPaginadoContarE";
			}else if(filtro.getTipodocumento().equals("C")) {
				parametro="personamast.listarPaginadoSentenciaC";
				contarParametro="personamast.listarPaginadoContarC";
			}
		}else {
			parametro="personamast.listarPaginadoSentencia";
			contarParametro="personamast.listarPaginadoContar";
		}
			
			
		Integer registros = contar(contarParametro, parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, parametro,DtlComunPersonamast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarPersonas( FiltroComunPersonamast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		
		String parametro="";
		String contarParametro="";

		if (UInteger.esCeroOrNulo(filtro.getPersona()))
			filtro.setPersona(null);
		if (UString.esNuloVacio(filtro.getBusqueda()))
			filtro.setBusqueda("");
		if (UString.esNuloVacio(filtro.getDocumento()))
			filtro.setDocumento("");
		if (UString.esNuloVacio(filtro.getDocumentofiscal()))
			filtro.setDocumentofiscal("");
		if (UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setDocumentoidentidad("");
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		
		
		if (UInteger.esCeroOrNulo(filtro.getPersona()) &&  UString.esNuloVacio(filtro.getBusqueda()) &&
				UString.esNuloVacio(filtro.getDocumento()) && UString.esNuloVacio(filtro.getDocumentofiscal()) && UString.esNuloVacio(filtro.getDocumentoidentidad()))
			filtro.setBusqueda("A");
			
			
		
		// TODO Personamast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getBusqueda()));
		parametros.add(new DominioParametroPersistencia("p_documento", String.class, filtro.getDocumento()));
		parametros.add(new DominioParametroPersistencia("p_documentofiscal", String.class, filtro.getDocumentofiscal()));
		parametros.add(new DominioParametroPersistencia("p_documentoidentidad", String.class, filtro.getDocumentoidentidad()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		if(!UString.esNuloVacio(filtro.getTipodocumento())) {
						
			if(filtro.getTipodocumento().equals("S")) {
				parametro="personamast.listarPaginadoSentenciaP";
				contarParametro="personamast.listarPaginadoContarP";
			}else if(filtro.getTipodocumento().equals("E")) {
				parametro="personamast.listarPaginadoSentenciaE";
				contarParametro="personamast.listarPaginadoContarE";
			}else if(filtro.getTipodocumento().equals("C")) {
				parametro="personamast.listarPaginadoSentenciaC";
				contarParametro="personamast.listarPaginadoContarC";
			}
		}else {
			parametro="personamast.listarPaginadoSentencia";
			contarParametro="personamast.listarPaginadoContar";
		}
			
		List lst = this.listarPorQuery(DtlComunPersonamast.class, parametro, parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	
	@Transactional
	public List<DominioMensajeUsuario> validarEstadoGestionProveedor(Integer idPersona) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanPersonamast per = this.obtenerPorId(idPersona);
		if (per==null) {
			lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Proveedor no encontrado"));
			return lst;
		}		
		String es = UString.obtenerValorCadenaSinNulo(per.getEstadogestionproveedor());
		if (!es.equals(SpringWhConstanteBoot.PROCESO_PROVEEDORES_ESTADO_ACTIVO))
			lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Proveedor no debe estar activo en su proceso de evaluaci\u00f3n o pendiente de registro en la segunda etapa."));
        return lst;
	}
}
