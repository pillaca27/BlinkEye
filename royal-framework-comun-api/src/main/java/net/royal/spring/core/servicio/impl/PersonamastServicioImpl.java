package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.core.dao.impl.PersonamastWhDaoImpl;

import net.royal.spring.core.dominio.BeanPersonamast;
import net.royal.spring.core.dominio.BeanPersonamastPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonacuentabancaria;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonapersonagrupo;
import net.royal.spring.core.dominio.dto.DtoComunPersonaMast;
import net.royal.spring.core.servicio.validar.PersonamastServicioValidar;

@Service (value = "BeanServicioPersonamast")
public class PersonamastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioPersonamast";
	private static Logger logger = LogManager.getLogger(PersonamastServicioImpl.class);

	@Autowired
	private PersonamastWhDaoImpl personamastDao;

	@Autowired
	private PersonamastServicioValidar validar;
	
	@Autowired
	private ProveedormastServicioImpl proveedorService;
	
	@Autowired
	private MaPersonapersonagrupoServicioImpl personaGrupoService;
	
	@Autowired
	private MaPersonacuentabancariaServicioImpl cuentasBancariasService;

	

	@Transactional
	public DtoComunPersonaMast coreActualizarMasivo(SeguridadUsuarioActual usuarioActual,List<DtoComunPersonaMast> dto) throws UException {
		
		DtoComunPersonaMast dw = new DtoComunPersonaMast();
		for (DtoComunPersonaMast DtoPersonaMastWh : dto) {
			String correo=DtoPersonaMastWh.getCorreoelectronicootros();
			dw= personamastDao.obtenerDto(DtoPersonaMastWh);
			dw.setCorreoelectronicootros(correo);
			coreActualizarSimple(usuarioActual, dw);
		}
		return dw;
	}
	
	@Transactional
	public DtoComunPersonaMast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunPersonaMast dto) throws UException {
		
		logger.debug("Usuario " + usuarioActual.getUsuario());
		BeanPersonamast personamast = dto.obtenerBean();
		personamast = coreInsertar(usuarioActual, personamast,dto);
		
		dto.setTransaccionEstado(personamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(personamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPersonamast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast,DtoComunPersonaMast dto) throws UException {
		// valores por defecto - preparando objeto
		personamast = validar.prepararInsertar(usuarioActual, personamast);
		
		personamast.setFlagRuc(dto.getFlagRuc());
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, personamast);
		if (!lst.isEmpty()) {
			personamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			personamast.setTransaccionListaMensajes(lst);
			return personamast;
		}
		
		// transaccion
		personamast.setOrigen(usuarioActual.getUnidadNegocioAsignadaCodigo());
		personamast = personamastDao.coreInsertar(personamast);
		
		if(dto.getDtoMaPersonapersonagrupo().size() > 0) {
			
			for (DtoComunMaPersonapersonagrupo grupoPersonas : dto.getDtoMaPersonapersonagrupo()) {
				
				if(grupoPersonas.getAcciones().equals("NUEVO")) {
					grupoPersonas.setPersona(personamast.getPk().getPersona());
					personaGrupoService.coreInsertar(usuarioActual, grupoPersonas);
				}			
			}
		}
		
		if(dto.getDtoPersonacuentabancaria().size() > 0) {
			
			for (DtoComunMaPersonacuentabancaria cuentasBancarias : dto.getDtoPersonacuentabancaria()) {
				
				if(cuentasBancarias.getAcciones().equals("NUEVO")) {
					cuentasBancarias.setPersona(personamast.getPk().getPersona());
					cuentasBancariasService.coreInsertar(usuarioActual, cuentasBancarias);
				}			
			}
		}
		
		personamast.setTransaccionEstado(DominioTransaccion.OK);
		personamast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return personamast;
	}

	@Transactional
	public DtoComunPersonaMast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunPersonaMast dto) throws UException {
		BeanPersonamast personamast = dto.obtenerBean();
		personamast = coreActualizar(usuarioActual,personamast, dto);	
			
		dto.setTransaccionEstado(personamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(personamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPersonamast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanPersonamast personamast,DtoComunPersonaMast dto) throws UException {
		// valores por defecto - preparando objeto
		personamast = validar.prepararActualizar(usuarioActual, personamast);
		
		personamast.setFlagRuc(dto.getFlagRuc());
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, personamast);
		if (!lst.isEmpty()) {
			personamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			personamast.setTransaccionListaMensajes(lst);
			return personamast;
		}
		
		// transaccion
	    personamast = personamastDao.coreActualizar(personamast);
	    
		if(dto.getDtoMaPersonapersonagrupo().size() > 0) {
			
			for (DtoComunMaPersonapersonagrupo grupoPersonas : dto.getDtoMaPersonapersonagrupo()) {
				
				if(grupoPersonas.getAcciones().equals("NUEVO")) {
					personaGrupoService.coreInsertar(usuarioActual, grupoPersonas);
				}else if(grupoPersonas.getAcciones().equals("UPDATE")) {
					personaGrupoService.coreActualizar(usuarioActual, grupoPersonas);
				}else if(grupoPersonas.getAcciones().equals("DELETE")) {
					personaGrupoService.coreEliminar(usuarioActual, grupoPersonas);
				}			
			}
		}
		
		if(dto.getDtoPersonacuentabancaria().size() > 0) {
			
			for (DtoComunMaPersonacuentabancaria cuentasBancarias : dto.getDtoPersonacuentabancaria()) {
				
				if(cuentasBancarias.getAcciones().equals("NUEVO")) {
					cuentasBancariasService.coreInsertar(usuarioActual, cuentasBancarias);
				}else if(cuentasBancarias.getAcciones().equals("UPDATE")) {
					cuentasBancariasService.coreActualizar(usuarioActual, cuentasBancarias);
				}else if(cuentasBancarias.getAcciones().equals("DELETE")) {
					cuentasBancariasService.coreEliminar(usuarioActual, cuentasBancarias);
				}			
			}
		}
		
		personamast.setTransaccionEstado(DominioTransaccion.OK);
		personamast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return personamast;
	}
	
	@Transactional
	public DtoComunPersonaMast coreActualizarSimple(SeguridadUsuarioActual usuarioActual,DtoComunPersonaMast dto) throws UException {
		BeanPersonamast personamast = dto.obtenerBean();
		personamast = coreActualizarSimple(usuarioActual,personamast, dto);	
			
		dto.setTransaccionEstado(personamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(personamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPersonamast coreActualizarSimple(SeguridadUsuarioActual usuarioActual, BeanPersonamast personamast,DtoComunPersonaMast dto) throws UException {
		// valores por defecto - preparando objeto
		personamast = validar.prepararActualizar(usuarioActual, personamast);
		
		// transaccion
	    personamast = personamastDao.coreActualizar(personamast);
	    
		personamast.setTransaccionEstado(DominioTransaccion.OK);
		personamast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return personamast;
	}


	@Transactional
	public DtoComunPersonaMast coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunPersonaMast dto) throws UException {
		BeanPersonamast personamast = dto.obtenerBean();
		personamast = coreAnular(usuarioActual, personamast);
		dto.setTransaccionEstado(personamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(personamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPersonamast coreAnular(SeguridadUsuarioActual usuarioActual, BeanPersonamast personamast) throws UException {
		// valores por defecto - preparando objeto
		personamast = validar.prepararAnular(usuarioActual, personamast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, personamast);
		if (!lst.isEmpty()) {
			personamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			personamast.setTransaccionListaMensajes(lst);
			return personamast;
		}
		
		// transaccion
	    personamast.setEstado("I");
	    personamast = personamastDao.coreActualizar(personamast);
		personamast.setTransaccionEstado(DominioTransaccion.OK);
		personamast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return personamast;
	}

	public BeanPersonamast coreAnular(SeguridadUsuarioActual usuarioActual, BeanPersonamastPk pk) throws UException {
		BeanPersonamast bean = personamastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanPersonamast coreAnular(SeguridadUsuarioActual usuarioActual, Integer ppersona) throws UException {
		return coreAnular(usuarioActual,new BeanPersonamastPk( ppersona));
	}

	@Transactional
	public DtoComunPersonaMast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunPersonaMast dto) throws UException {
		BeanPersonamast personamast = dto.obtenerBean();
		personamast = coreEliminar(usuarioActual, personamast);
		dto.setTransaccionEstado(personamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(personamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPersonamast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanPersonamast personamast) throws UException {
		// valores por defecto - preparando objeto
		personamast = validar.prepararEliminar(usuarioActual, personamast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, personamast);
		if (!lst.isEmpty()) {
			personamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			personamast.setTransaccionListaMensajes(lst);
			return personamast;
		}
		
		// transaccion
		personamastDao.eliminar(personamast);
		personamast=new BeanPersonamast();
		personamast.setTransaccionEstado(DominioTransaccion.OK);
		return personamast;
	}

	public BeanPersonamast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanPersonamastPk pk) throws UException {
		BeanPersonamast personamast = personamastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,personamast);
	}

	public BeanPersonamast coreEliminar(SeguridadUsuarioActual usuarioActual, Integer ppersona) throws UException {
		return coreEliminar(usuarioActual,new BeanPersonamastPk( ppersona));
	}

}
