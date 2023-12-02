package net.royal.spring.logistica.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhClaselinea;
import net.royal.spring.logistica.dominio.BeanWhClaselineaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasefamilia;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClaselinea;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasesubfamilia;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhClaselinea;


@Repository
public class WhClaselineaDaoImpl extends GenericoDaoImpl<BeanWhClaselinea, BeanWhClaselineaPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(BeanWhClaselinea.class);

	public WhClaselineaDaoImpl() {
		super("whclaselinea");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanWhClaselinea obtenerPorId(String plinea) {
		return obtenerPorId(new BeanWhClaselineaPk( plinea));
	}

	public BeanWhClaselinea coreInsertar(BeanWhClaselinea bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanWhClaselinea coreActualizar(BeanWhClaselinea bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunWhClaselinea obtenerDtoCore(DtoComunWhClaselinea pk) throws Exception {
		DtoComunWhClaselinea dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunWhClaselinea();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunWhClaselinea> listarDtoCore(DtoComunWhClaselinea filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WhClaselinea.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));

		List datos = this.listarPorQuery(DtoComunWhClaselinea.class, "whclaselinea.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunWhClaselinea obtenerDto(DtoComunWhClaselinea pk) {
		DtoComunWhClaselinea dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));

		List lst = listarPorQuery(DtoComunWhClaselinea.class, "whclaselinea.obtenerDtoLineas", parametros);
		if (lst.size() == 1)
			dto = (DtoComunWhClaselinea) lst.get(0);
		
		List<DominioParametroPersistencia> parametrosDetalle = new ArrayList<DominioParametroPersistencia>();
		parametrosDetalle.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		List lstDetalle = listarPorQuery(DtoComunWhClasefamilia.class, "whclaselinea.obtenerDetalleDto", parametrosDetalle);
		
		if(lstDetalle.size()> 0) {
			dto.setLstDetalle(lstDetalle);
		}
		return dto;
	}

	public DtoComunWhClaselinea obtenerDtoSubFamilia(DtoComunWhClaselinea pk) {
		DtoComunWhClaselinea dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));

		List lst = listarPorQuery(DtoComunWhClaselinea.class, "whclaselinea.obtenerDtoLineas", parametros);
		if (lst.size() == 1)
			dto = (DtoComunWhClaselinea) lst.get(0);
		
		List<DominioParametroPersistencia> parametrosDetalle = new ArrayList<DominioParametroPersistencia>();
		parametrosDetalle.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		parametrosDetalle.add(new DominioParametroPersistencia("p_familia", String.class, pk.getFamilia()));
		List lstDetalleSubFamilia = listarPorQuery(DtoComunWhClasesubfamilia.class, "whclaselinea.obtenerDetalleSubFamiliaDto", parametrosDetalle);
		
		if(lstDetalleSubFamilia.size()> 0) {
			dto.setLstDetalleSubFamilia(lstDetalleSubFamilia);
		}
		return dto;
	}
	
	
	public List<DtoTabla> validarCuentas(String cuenta) {
		logger.debug("MaPersonagrupoRest.validarCuentas");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_cuenta", String.class,cuenta));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "whclaselinea.validarCuentasL",parametros);
		
		return datos;
	}

	public DominioPaginacion exportarLineas( FiltroComunWhClaselinea filtro) {

		if (UString.esNuloVacio(filtro.getLinea()))
			filtro.setLinea("");

		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getLinea()));
        List lista = this.listarPorQuery(DtoComunWhClaselinea.class, "whclaselinea.listarLineas",parametros);
        filtro.getPaginacion().setPaginacionListaResultado(lista);   
		return filtro.getPaginacion();
	}
}
