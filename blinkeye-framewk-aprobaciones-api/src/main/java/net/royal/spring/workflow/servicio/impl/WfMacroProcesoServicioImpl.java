package net.royal.spring.workflow.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
import net.royal.spring.workflow.dao.impl.WfMacroProcesoDaoImpl;

import net.royal.spring.workflow.dominio.WfMacroProceso;
import net.royal.spring.workflow.dominio.WfMacroProcesoPk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProceso;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalle;
import net.royal.spring.workflow.servicio.validar.WfMacroProcesoServicioValidar;

@Service (value = "BeanServicioWfMacroProceso")
public class WfMacroProcesoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioWfMacroProceso";
	private static Logger logger = LogManager.getLogger(WfMacroProcesoServicioImpl.class);

	@Autowired
	private WfMacroProcesoDaoImpl wfMacroProcesoDao;
	
	@Autowired
	private WfMacroProcesoDetalleServicioImpl wfMacroProcesoDetalleServicioImpl;

	@Autowired
	private WfMacroProcesoServicioValidar validar;

	@Transactional
	public DtoWfMacroProceso coreInsertar(SeguridadUsuarioActual usuarioActual,DtoWfMacroProceso dto) throws UException {
		//VALIDAR GRILLA
		if(!this.validarData(dto).getTransaccionListaMensajes().isEmpty()) {
			return dto;
		}
		
		dto.setMacroProcesoId(wfMacroProcesoDao.generarSecuencia());
		WfMacroProceso wfMacroProceso = dto.obtenerBean();
		wfMacroProceso = coreInsertar(usuarioActual, wfMacroProceso);
		dto.setTransaccionEstado(wfMacroProceso.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfMacroProceso.getTransaccionListaMensajes());
 	
		for (DtoWfMacroProcesoDetalle obj : dto.getDetalle()) {
			obj.setMacroProcesoId(dto.getMacroProcesoId());
			wfMacroProcesoDetalleServicioImpl.coreInsertar(usuarioActual, obj);
		}
		

		return dto;
	}
	
	public DtoWfMacroProceso validarData(DtoWfMacroProceso dto) {
		List<DominioMensajeUsuario> msj = new ArrayList<DominioMensajeUsuario>();
		int count = 1;
		int indice_obj = 0;
		
		for (DtoWfMacroProcesoDetalle obj : dto.getDetalle()) {
			boolean valida = false;
			
			if(obj.getProcesoOrigenId() != null && obj.getProcesoDestinoId() != null) {
				int indice_obja = 0;
				for (DtoWfMacroProcesoDetalle obja : dto.getDetalle()) {
					if(obj.getProcesoOrigenId().equals(obja.getProcesoOrigenId()) && obj.getProcesoDestinoId().equals(obja.getProcesoDestinoId()) && indice_obj != indice_obja) {
						
						valida=true;
					}
					indice_obja++;
				}
			} else {
				if(UString.estaVacio(obj.getProcesoOrigenId())) {
					msj.add(this.getMsjUsuarioError(this.getMsjUsuarioError("wfmacroprocesodetalle.procesoorigenid.isnull").getMensaje()+" Linea: "+count));
				}
				if(UString.estaVacio(obj.getProcesoDestinoId())) {
					msj.add(this.getMsjUsuarioError(this.getMsjUsuarioError("wfmacroprocesodetalle.procesodestinoid.isnull").getMensaje()+" Linea: "+count));
				}
			}
			
			if(valida) {
				msj.add(this.getMsjUsuarioError(this.getMsjUsuarioError("wfmacroprocesodetalle.procesoorigenydestino.repetido").getMensaje()+" Linea: "+count));
			}
			count++;
			indice_obj++;
			
		}
		
		if(!msj.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.setTransaccionListaMensajes(msj);
		}
		
		return dto;
	}

	@Transactional
	public WfMacroProceso coreInsertar(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso) throws UException {
		// valores por defecto - preparando objeto
		wfMacroProceso = validar.prepararInsertar(usuarioActual, wfMacroProceso);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, wfMacroProceso);
		if (!lst.isEmpty()) {
			wfMacroProceso.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfMacroProceso.setTransaccionListaMensajes(lst);
			return wfMacroProceso;
		}
		
		// transaccion
		wfMacroProceso = wfMacroProcesoDao.coreInsertar(wfMacroProceso);
		wfMacroProceso.setTransaccionEstado(DominioTransaccion.OK);
		wfMacroProceso.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfMacroProceso;
	}

	@Transactional
	public DtoWfMacroProceso coreActualizar(SeguridadUsuarioActual usuarioActual,DtoWfMacroProceso dto) throws UException {
		if(!this.validarData(dto).getTransaccionListaMensajes().isEmpty()) {
			return dto;
		}
		
		WfMacroProceso wfMacroProceso = dto.obtenerBean();
		wfMacroProceso = coreActualizar(usuarioActual, wfMacroProceso);
		dto.setTransaccionEstado(wfMacroProceso.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfMacroProceso.getTransaccionListaMensajes());
		
		for (DtoWfMacroProcesoDetalle obj : dto.getDetalle()) {
			//obj.setMacroProcesoId(dto.getMacroProcesoId());
			wfMacroProcesoDetalleServicioImpl.coreActualizar(usuarioActual, obj);
		}
		return dto;
	}

	@Transactional
	public WfMacroProceso coreActualizar(SeguridadUsuarioActual usuarioActual, WfMacroProceso wfMacroProceso) throws UException {
		// valores por defecto - preparando objeto
		wfMacroProceso = validar.prepararActualizar(usuarioActual, wfMacroProceso);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, wfMacroProceso);
		if (!lst.isEmpty()) {
			wfMacroProceso.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfMacroProceso.setTransaccionListaMensajes(lst);
			return wfMacroProceso;
		}
		
		// transaccion
	    wfMacroProceso = wfMacroProcesoDao.coreActualizar(wfMacroProceso);
		wfMacroProceso.setTransaccionEstado(DominioTransaccion.OK);
		wfMacroProceso.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfMacroProceso;
	}


	@Transactional
	public DtoWfMacroProceso coreAnular(SeguridadUsuarioActual usuarioActual,DtoWfMacroProceso dto) throws UException {
		WfMacroProceso wfMacroProceso = dto.obtenerBean();
		wfMacroProceso = coreAnular(usuarioActual, wfMacroProceso);
		dto.setTransaccionEstado(wfMacroProceso.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfMacroProceso.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfMacroProceso coreAnular(SeguridadUsuarioActual usuarioActual, WfMacroProceso wfMacroProceso) throws UException {
		// valores por defecto - preparando objeto
		wfMacroProceso = validar.prepararAnular(usuarioActual, wfMacroProceso);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, wfMacroProceso);
		if (!lst.isEmpty()) {
			wfMacroProceso.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfMacroProceso.setTransaccionListaMensajes(lst);
			return wfMacroProceso;
		}
		
		// transaccion
	    wfMacroProceso = wfMacroProcesoDao.coreActualizar(wfMacroProceso);
		wfMacroProceso.setTransaccionEstado(DominioTransaccion.OK);
		wfMacroProceso.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return wfMacroProceso;
	}

	public WfMacroProceso coreAnular(SeguridadUsuarioActual usuarioActual, WfMacroProcesoPk pk) throws UException {
		WfMacroProceso bean = wfMacroProcesoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public WfMacroProceso coreAnular(SeguridadUsuarioActual usuarioActual, String pmacroProcesoId) throws UException {
		return coreAnular(usuarioActual,new WfMacroProcesoPk( pmacroProcesoId));
	}

	@Transactional
	public DtoWfMacroProceso coreEliminar(SeguridadUsuarioActual usuarioActual,DtoWfMacroProceso dto) throws UException {
		WfMacroProceso wfMacroProceso = dto.obtenerBean();
		wfMacroProceso = coreEliminar(usuarioActual, wfMacroProceso);
		dto.setTransaccionEstado(wfMacroProceso.getTransaccionEstado());
		dto.setTransaccionListaMensajes(wfMacroProceso.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public WfMacroProceso coreEliminar(SeguridadUsuarioActual usuarioActual, WfMacroProceso wfMacroProceso) throws UException {
		// valores por defecto - preparando objeto
		wfMacroProceso = validar.prepararEliminar(usuarioActual, wfMacroProceso);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, wfMacroProceso);
		if (!lst.isEmpty()) {
			wfMacroProceso.setTransaccionEstado(DominioTransaccion.VALIDACION);
			wfMacroProceso.setTransaccionListaMensajes(lst);
			return wfMacroProceso;
		}
		
		// transaccion
		wfMacroProcesoDao.eliminar(wfMacroProceso);
		wfMacroProceso=new WfMacroProceso();
		wfMacroProceso.setTransaccionEstado(DominioTransaccion.OK);
		return wfMacroProceso;
	}

	public WfMacroProceso coreEliminar(SeguridadUsuarioActual usuarioActual, WfMacroProcesoPk pk) throws UException {
		WfMacroProceso wfMacroProceso = wfMacroProcesoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,wfMacroProceso);
	}

	public WfMacroProceso coreEliminar(SeguridadUsuarioActual usuarioActual, String pmacroProcesoId) throws UException {
		return coreEliminar(usuarioActual,new WfMacroProcesoPk( pmacroProcesoId));
	}

}
