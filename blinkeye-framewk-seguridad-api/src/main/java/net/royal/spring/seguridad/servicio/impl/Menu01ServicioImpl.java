package net.royal.spring.seguridad.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenu;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenuItem;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.seguridad.dao.impl.Menu01DaoImpl;
import net.royal.spring.seguridad.dao.impl.UsuarioSeguridadDaoImpl;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;

@Service(value = "BeanServicioMenu01")
public class Menu01ServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMenu01";
	private static Logger logger = LogManager.getLogger(Menu01ServicioImpl.class);

	@Autowired
	private Menu01DaoImpl usuarioDao;
	
	public String usuarioEsAdministradorDeAplicacion(String aplicacionId,String usuarioId) throws Exception {
		return usuarioDao.usuarioEsAdministradorDeAplicacion(aplicacionId,usuarioId);
	}
	
	public SeguridadMenu menuPorAplicacionUsuario(String aplicacionId, String usuarioId) throws Exception {

//		if(aplicacionId.equals("PE")) 
//			return menuPostulantes();
		/***/
		SeguridadMenu mnu = new SeguridadMenu();
		/***/
		logger.debug("perfiles:"+usuarioId);
				
		/* LISTAR CONCEPTOS DESDE AUTORIZACION SOLO DEL PRIMER PERFIL */
		List<DtoSegAutorizacion> lstTodos = usuarioDao.listarDtoAutorizacionesPorUsuarioTodos(aplicacionId,usuarioId);
		List<DtoSegAutorizacion> lstGruposTodos = usuarioDao.listarDtoAutorizacionesGruposPorAplicacion(aplicacionId);
		
		List<SeguridadMenuItem> lstGrupos = new ArrayList<SeguridadMenuItem>();
		List<SeguridadMenuItem> lstEncontrados = new ArrayList<SeguridadMenuItem>();
		
		for (DtoSegAutorizacion dtoSegAutorizacion : lstTodos) {
			//logger.debug("====> Componente id:"+dtoSegAutorizacion.getAplicacionId()+dtoSegAutorizacion.getContenedorId()+dtoSegAutorizacion.getComponenteId()+" : " + dtoSegAutorizacion.getAuxComponenteNombre());
						
			// Identificar Grupos
			SeguridadMenuItem mnuGrp = lstEncontrados.stream()
				.filter(u -> u.getAplicacioncodigo().equals(dtoSegAutorizacion.getAplicacionId()))
				.filter(u -> u.getGrupoCodigo().equals(dtoSegAutorizacion.getContenedorId()))
				.findFirst().orElse(null);
			if (mnuGrp==null) {
				List lst = usuarioDao.armarArbol(lstEncontrados, lstGrupos, lstGruposTodos,dtoSegAutorizacion);
				lstEncontrados = (List<SeguridadMenuItem>)lst.get(0);
				lstGrupos = (List<SeguridadMenuItem>)lst.get(1);
			}

			lstGrupos = usuarioDao.asignarConcepto(lstGrupos,dtoSegAutorizacion);
			
		}
		
		mnu.setItems(lstGrupos);
				
		usuarioDao.pintarMenu(lstGrupos);
		
		return mnu;
	}

}
