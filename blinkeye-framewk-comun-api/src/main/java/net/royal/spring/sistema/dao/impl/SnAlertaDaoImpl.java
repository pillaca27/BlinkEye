package net.royal.spring.sistema.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSnAlerta;
import net.royal.spring.sistema.dominio.BeanSnAlertaPk;
import net.royal.spring.sistema.dominio.BeanSyApi;
import net.royal.spring.sistema.dominio.BeanSyApiPk;
import net.royal.spring.sistema.dominio.dto.DtoComunDashboardComunicacion;
import net.royal.spring.sistema.dominio.dto.DtoComunSnAlertas;
import net.royal.spring.sistema.dominio.dto.DtoComunDashboard;
import net.royal.spring.sistema.dominio.dto.DtoComunWsApipath;
import net.royal.spring.sistema.dominio.filtro.FiltroComunApi;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyReporte;
import net.royal.spring.sistema.dominio.lista.DtlComunSyReporte;

@Repository
public class SnAlertaDaoImpl extends GenericoDaoImpl<BeanSnAlerta, BeanSnAlertaPk> {

	private static final long serialVersionUID = 1L;

	public SnAlertaDaoImpl() {
		super("snalertascomun");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	@Transactional
	public DtoComunDashboard listaractivos(SeguridadUsuarioActual usuario) {		
		DtoComunDashboard dash = new DtoComunDashboard();		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, usuario.getPersonaId()));
		List datos = listarPorQuery(DtoComunSnAlertas.class, "snalertascomun.listaractivos", parametros);
		dash.setLstAlerta(datos);			
		return dash;
	}
	
	
	@Transactional
	public DtoComunSnAlertas actualizarAlerta(DtoComunSnAlertas dto) {
		
//		SnAlertaPk pk = new SnAlertaPk();
//		pk.setAlertaId(dto.getAlertaid());
//		
//		SnAlerta bean = this.obtenerPorId(pk);
		
		//if(bean != null) {
			//bean.setESTADO("I");
			//this.actualizar(bean);
		//}


		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_alertaId", Integer.class, dto.getAlertaid()));
//		parametros.add(new DominioParametroPersistencia("p_codigoexternoid", String.class, bean.getCodigoExternoId()));
//		parametros.add(new DominioParametroPersistencia("p_codigoprocesowf", String.class, bean.getCodigoProcesoWf()));
		this.ejecutarPorQuery("snalertascomun.actualizarRegistros", parametros);
		
		return dto;
	}

	
	@Transactional
	public DtoComunSnAlertas registrarAlerta(DtoComunSnAlertas dto, SeguridadUsuarioActual usuario) {
		
		BeanSnAlertaPk pk = new BeanSnAlertaPk();
		BeanSnAlerta bean = new BeanSnAlerta();
			
			pk.setAlertaId(this.generarId());
			bean.setPk(pk);
			if(dto.getPersonaId() == null) {
				bean.setPersonaId(usuario.getPersonaId());	
			}else {
				bean.setPersonaId(dto.getPersonaId());
			}
			bean.setESTADO("A");
			bean.setNombre(dto.getNombre());
			
			bean.setAccionWf(dto.getAccionWf());
			bean.setCodigoProcesoWf(dto.getCodigoProcesoWf());
			bean.setCodigoExternoId(dto.getCodigoExternoId());
			bean.setTransaccionWfId(dto.getTransaccionWfId());
			
			bean.setLink(SpringComunConstanteBoot.URL_PROVEEDOR +""+dto.getAccion() + SpringComunConstanteBoot.JSONARMADO + dto.getLink() +"}" );
			
			bean.setUltimaFechaModif(new Date());
			bean.setUltimoUsuario(usuario.getUsuario());
			
			this.registrar(bean);
		
		
		return dto;
	}
	
	public Integer generarId() {
		Criteria criteria = getCriteria().setProjection(Projections.max("pk.alertaId"));
		Integer calc = (Integer) criteria.uniqueResult();

		if (calc == null) {
			return 1;
		}
		return calc + 1;
	}
	
	public List<DtoComunDashboardComunicacion> listarEstudioActivosPorProveedorConsultas(Integer proveedorId) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, proveedorId));
		List datos = this.listarPorQuery(DtoComunDashboardComunicacion.class, "snalertascomun.listarEstudioActivosPorProveedorConsultas", parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	public DtoComunDashboardComunicacion obtenerGestorCotizacion(DtoComunDashboardComunicacion cotizacion,Integer CotizacionSecuencia) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_cotizacionsecuencia", Integer.class, CotizacionSecuencia));
		List datos = this.listarPorQuery(DtoComunDashboardComunicacion.class, "snalertascomun.obtenerGestorCotizacion", parametros);
		logger.debug(datos.size());
		if (datos.size()==1) {
			DtoComunDashboardComunicacion t = (DtoComunDashboardComunicacion)datos.get(0);
			cotizacion.setPersonaId(t.getPersonaId());
			cotizacion.setUsuario(t.getUsuario());
			cotizacion.setExternoPk6(t.getExternoPk6());
			cotizacion.setExternoPk7(t.getExternoPk7());
		}
		return cotizacion;
	}
	
	public DtoComunDashboardComunicacion obtenerSubastasProveedorCantidad(Integer proveedorId) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, proveedorId));
		List datos = this.listarPorQuery(DtoComunDashboardComunicacion.class, "snalertascomun.obtenerSubastasProveedorCantidad", parametros);
		logger.debug(datos.size());
		if (datos.size()==1) {
			DtoComunDashboardComunicacion t = (DtoComunDashboardComunicacion)datos.get(0);
			return t;
		}
		return new DtoComunDashboardComunicacion();
	}
	public DtoComunDashboardComunicacion obtenerCotizacionProveedorCantidad(Integer proveedorId) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, proveedorId));
		List datos = this.listarPorQuery(DtoComunDashboardComunicacion.class, "snalertascomun.obtenerCotizacionProveedorCantidad", parametros);
		logger.debug(datos.size());
		if (datos.size()==1) {
			DtoComunDashboardComunicacion t = (DtoComunDashboardComunicacion)datos.get(0);
			return t;
		}
		return new DtoComunDashboardComunicacion();
	}
	public DtoComunDashboardComunicacion obtenerGestionProveedorCantidad(Integer proveedorId) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, proveedorId));
		List datos = this.listarPorQuery(DtoComunDashboardComunicacion.class, "snalertascomun.obtenerGestionProveedorCantidad", parametros);
		logger.debug(datos.size());
		if (datos.size()==1) {
			DtoComunDashboardComunicacion t = (DtoComunDashboardComunicacion)datos.get(0);
			return t;
		}
		return new DtoComunDashboardComunicacion();
	}
	public String obtenerGestionProveedorEstado(Integer proveedorId) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, proveedorId));
		List datos = this.listarPorQuery(DtoTabla.class, "snalertascomun.obtenerGestionProveedorEstado", parametros);
		logger.debug(datos.size());
		if (datos.size()==1) {
			DtoTabla t = (DtoTabla)datos.get(0);
			return t.getCodigo();
		}
		return null;
	}
	public DtoComunDashboardComunicacion obtenerConvocatoriaProveedorCantidad(Integer proveedorId) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", Integer.class, proveedorId));
		List datos = this.listarPorQuery(DtoComunDashboardComunicacion.class, "snalertascomun.obtenerConvocatoriaProveedorCantidad", parametros);
		logger.debug(datos.size());
		if (datos.size()==1) {
			DtoComunDashboardComunicacion t = (DtoComunDashboardComunicacion)datos.get(0);
			return t;
		}
		return new DtoComunDashboardComunicacion();
	}
}
