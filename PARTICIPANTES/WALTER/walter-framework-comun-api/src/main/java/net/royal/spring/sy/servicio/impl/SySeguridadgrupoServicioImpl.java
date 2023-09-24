package net.royal.spring.sy.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sy.dao.impl.SySeguridadgrupoDaoImpl;
import net.royal.spring.sy.dominio.BeanSySeguridadgrupo;
import net.royal.spring.sy.dominio.BeanSySeguridadgrupoPk;
import net.royal.spring.sy.dominio.dto.DtoComunSySeguridadconcepto;
import net.royal.spring.sy.dominio.dto.DtoComunSySeguridadgrupo;
import net.royal.spring.sy.servicio.validar.SySeguridadgrupoServicioValidar;

@Service (value = "BeanServicioSySeguridadgrupo")
public class SySeguridadgrupoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSySeguridadgrupo";
	private static Logger logger = LogManager.getLogger(SySeguridadgrupoServicioImpl.class);

	@Autowired
	private SySeguridadgrupoDaoImpl sySeguridadgrupoDao;

	@Autowired
	private SySeguridadgrupoServicioValidar validar;
	
	@Autowired
	private SySeguridadconceptoServicioImpl serviciodetalle;

	@Transactional
	public DtoComunSySeguridadgrupo coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadgrupo dto) throws Exception {
		
		if(!this.validarDatos(dto).getTransaccionListaMensajes().isEmpty()) {
			return dto;
		}
		
		BeanSySeguridadgrupo sySeguridadgrupo = dto.obtenerBean();
		sySeguridadgrupo = coreInsertar(usuarioActual, sySeguridadgrupo);
		dto.setTransaccionEstado(sySeguridadgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadgrupo.getTransaccionListaMensajes());
		
		for (DtoComunSySeguridadconcepto obj : dto.getSeguridadconcepto()) {
			obj.setAplicacioncodigo(sySeguridadgrupo.getPk().getAplicacioncodigo());
			obj.setGrupo(sySeguridadgrupo.getPk().getGrupo());
			serviciodetalle.coreInsertar(usuarioActual, obj);
		}
		
		
		return dto;
	}
	
	public DtoComunSySeguridadgrupo validarDatos(DtoComunSySeguridadgrupo dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		 if(UString.estaVacio(dto.getAplicacioncodigo())) {
			 lst.add(this.getMsjUsuarioError("syseguridadgrupo.aplicacioncodigo.notnull"));
		 }
		 if(UString.estaVacio(dto.getGrupo())) {
			 lst.add(this.getMsjUsuarioError("syseguridadgrupo.grupo.notnull"));
		 }
		boolean valida = true;
		for (DtoComunSySeguridadconcepto obj : dto.getSeguridadconcepto()) {
			if(UString.estaVacio(obj.getConcepto())) {
				 lst.add(this.getMsjUsuarioError("syseguridadconcepto.concepto.notnull"));
			 }
			 if(UString.estaVacio(obj.getDescripcionlocal())) {
				 lst.add(this.getMsjUsuarioError("syseguridadconcepto.descripcion.notnull"));
			 }
			 if(!UString.estaVacio(obj.getConcepto()) && !UString.estaVacio(obj.getDescripcionlocal())) {
				 List<DtoComunSySeguridadconcepto> datorepetido = dto.getSeguridadconcepto().stream().filter(p->p.getConcepto().equals(obj.getConcepto())).collect(Collectors.toList());
				 if(datorepetido.size() > 1  ) {
				 	if(valida) {
				 		lst.add(this.getMsjUsuarioError("El Concepto: "+datorepetido.get(0).getConcepto()+" Est\u00e1 Repetido"));
				 		valida = false;
				 	}else {
				 		valida = true;
				 	}
				 }
			 }
		}
		if(!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.setTransaccionListaMensajes(lst);	
		}

		return dto;
	}

	@Transactional
	public BeanSySeguridadgrupo coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		// valores por defecto - preparando objeto
		sySeguridadgrupo = validar.prepararInsertar(usuarioActual, sySeguridadgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, sySeguridadgrupo);
		if (!lst.isEmpty()) {
			sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadgrupo.setTransaccionListaMensajes(lst);
			return sySeguridadgrupo;
		}
		
		// transaccion
		sySeguridadgrupo = sySeguridadgrupoDao.coreInsertar(sySeguridadgrupo);
		sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.OK);
		sySeguridadgrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return sySeguridadgrupo;
	}

	@Transactional
	public DtoComunSySeguridadgrupo coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadgrupo dto) throws Exception {
		BeanSySeguridadgrupo sySeguridadgrupo = dto.obtenerBean();
		sySeguridadgrupo = coreActualizar(usuarioActual, sySeguridadgrupo);
		dto.setTransaccionEstado(sySeguridadgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadgrupo.getTransaccionListaMensajes());
		
		for (DtoComunSySeguridadconcepto obj : dto.getSeguridadconcepto()) {
//			obj.setAplicacioncodigo(sySeguridadgrupo.getPk().getAplicacioncodigo());
//			obj.setGrupo(sySeguridadgrupo.getPk().getGrupo());
			if(obj.getFlgactualizar().equals("NUEVO")) {
				serviciodetalle.coreInsertar(usuarioActual, obj);
			}else {
				serviciodetalle.coreActualizar(usuarioActual, obj);
			}
			
		}
		return dto;
	}

	@Transactional
	public BeanSySeguridadgrupo coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		// valores por defecto - preparando objeto
		sySeguridadgrupo = validar.prepararActualizar(usuarioActual, sySeguridadgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, sySeguridadgrupo);
		if (!lst.isEmpty()) {
			sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadgrupo.setTransaccionListaMensajes(lst);
			return sySeguridadgrupo;
		}
		
		// transaccion
	    sySeguridadgrupo = sySeguridadgrupoDao.coreActualizar(sySeguridadgrupo);
		sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.OK);
		sySeguridadgrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return sySeguridadgrupo;
	}


	@Transactional
	public DtoComunSySeguridadgrupo coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadgrupo dto) throws Exception {
		BeanSySeguridadgrupo sySeguridadgrupo = dto.obtenerBean();
		sySeguridadgrupo = coreAnular(usuarioActual, sySeguridadgrupo);
		dto.setTransaccionEstado(sySeguridadgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSySeguridadgrupo coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		// valores por defecto - preparando objeto
		sySeguridadgrupo = validar.prepararAnular(usuarioActual, sySeguridadgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, sySeguridadgrupo);
		if (!lst.isEmpty()) {
			sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadgrupo.setTransaccionListaMensajes(lst);
			return sySeguridadgrupo;
		}
		
		// transaccion
	    sySeguridadgrupo.setEstado("I");
	    sySeguridadgrupo = sySeguridadgrupoDao.coreActualizar(sySeguridadgrupo);
		sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.OK);
		sySeguridadgrupo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return sySeguridadgrupo;
	}

	public BeanSySeguridadgrupo coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupoPk pk) throws Exception {
		BeanSySeguridadgrupo bean = sySeguridadgrupoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSySeguridadgrupo coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo) throws Exception {
		return coreAnular(usuarioActual,new BeanSySeguridadgrupoPk( paplicacioncodigo, pgrupo));
	}

	@Transactional
	public DtoComunSySeguridadgrupo coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSySeguridadgrupo dto) throws Exception {
		BeanSySeguridadgrupo sySeguridadgrupo = dto.obtenerBean();
		sySeguridadgrupo = coreEliminar(usuarioActual, sySeguridadgrupo);
		dto.setTransaccionEstado(sySeguridadgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(sySeguridadgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSySeguridadgrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		// valores por defecto - preparando objeto
		sySeguridadgrupo = validar.prepararEliminar(usuarioActual, sySeguridadgrupo);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, sySeguridadgrupo);
		if (!lst.isEmpty()) {
			sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			sySeguridadgrupo.setTransaccionListaMensajes(lst);
			return sySeguridadgrupo;
		}
		
		// transaccion
		sySeguridadgrupoDao.eliminar(sySeguridadgrupo);
		sySeguridadgrupo=new BeanSySeguridadgrupo();
		sySeguridadgrupo.setTransaccionEstado(DominioTransaccion.OK);
		return sySeguridadgrupo;
	}

	public BeanSySeguridadgrupo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupoPk pk) throws Exception {
		BeanSySeguridadgrupo sySeguridadgrupo = sySeguridadgrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,sySeguridadgrupo);
	}

	public BeanSySeguridadgrupo coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo) throws Exception {
		return coreEliminar(usuarioActual,new BeanSySeguridadgrupoPk( paplicacioncodigo, pgrupo));
	}

}
