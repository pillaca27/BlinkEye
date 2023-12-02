package net.royal.spring.seguridad.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.modelo.seguridad.SeguridadMenu;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenuItem;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.seguridad.dao.impl.Menu02DaoImpl;
import net.royal.spring.seguridad.dao.impl.UsuarioSeguridadDaoImpl;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;

@Service(value = "BeanServicioMenu02")
public class Menu02ServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioMenu02";
	private static Logger logger = LogManager.getLogger(Menu02ServicioImpl.class);

	@Autowired
	private Menu02DaoImpl usuarioDao;
	
	public String usuarioEsAdministradorDeAplicacion(String aplicacionId,String usuarioId) throws Exception {
		return usuarioDao.usuarioEsAdministradorDeAplicacion(aplicacionId,usuarioId);
	}
	
	private SeguridadMenu menuPostulantes() {
		SeguridadMenu m = new SeguridadMenu();
		List<SeguridadMenuItem> ls = new ArrayList<SeguridadMenuItem>();
		SeguridadMenuItem g = new SeguridadMenuItem("Mi Perfil", "fa fa-fw fa-user", null);
		
		ls.add(g);
		
		g.setItems(new ArrayList<>());
		
		g.getItems().add(new SeguridadMenuItem("Datos Personales", "fa fa-fw fa-male", "/spring/postulante/datos-personales"));
		g.getItems().add(new SeguridadMenuItem("Direcciones", "fa fa-fw fa-home", "/spring/postulante/direcciones"));
		g.getItems().add(new SeguridadMenuItem("Instrucci\u00F3n B\u00E1sica", "fa fa-fw fa-home", "/spring/postulante/instruccion-basica"));
		g.getItems().add(new SeguridadMenuItem("Instrucci\u00F3n Superior", "fa fa-fw fa-home", "/spring/postulante/instruccion-superior"));
		g.getItems().add(new SeguridadMenuItem("Cursos", "fa fa-fw fa-book", "/spring/postulante/cursos"));
		g.getItems().add(new SeguridadMenuItem("Idiomas", "fa fa-fw fa-language", "/spring/postulante/idiomas"));
		g.getItems().add(new SeguridadMenuItem("Inform\u00E1tica", "fa fa-fw fa-laptop", "/spring/postulante/informatica"));
		g.getItems().add(new SeguridadMenuItem("Experiencia", "fa fa-fw fa-briefcase", "/spring/postulante/experiencia"));
		g.getItems().add(new SeguridadMenuItem("Referencia", "fa fa-fw fa-phone", "/spring/postulante/referencia"));
		g.getItems().add(new SeguridadMenuItem("Dependientes", "fa fa-fw fa-users", "/spring/postulante/dependientes"));
		g.getItems().add(new SeguridadMenuItem("Documentos", "fa fa-fw fa-file-alt", "/spring/postulante/documentos"));

		ls.add(new SeguridadMenuItem("Postular", "fa fa-fw fa-flag", "/spring/postulante/postular"));
		m.setItems(ls);
		return m;
	}
	
	public SeguridadMenu menuPorAplicacionUsuario(String aplicacionId, String usuarioId) throws Exception {
		if(aplicacionId.equals("PE")) 
			return menuPostulantes();
		/***/
		SeguridadMenu mnu = new SeguridadMenu();
		/***/
		logger.debug("perfiles:"+usuarioId);
				
		/* LISTAR CONCEPTOS DESDE AUTORIZACION SOLO DEL PRIMER PERFIL */
		List<DtoSegAutorizacion> lstConceptosTodos = usuarioDao.listarDtoAutorizacionesPorUsuarioTodos(aplicacionId,usuarioId);
		List<DtoSegAutorizacion> lstGruposTodos = usuarioDao.listarDtoAutorizacionesGruposPorAplicacion(aplicacionId);
		logger.debug("====> lstGruposTodos.size()   :"+lstGruposTodos.size());
		logger.debug("====> lstConceptosTodos.size():"+lstConceptosTodos.size());
		
		List<SeguridadMenuItem> lstGrupos = new ArrayList<SeguridadMenuItem>();
		List<SeguridadMenuItem> lstEncontrados = new ArrayList<SeguridadMenuItem>();
		
		for (DtoSegAutorizacion dtoSegAutorizacion : lstConceptosTodos) {
			//logger.debug("====> Componente id:"+dtoSegAutorizacion.getAplicacionId()+dtoSegAutorizacion.getContenedorWebGrupo()+dtoSegAutorizacion.getComponenteId()+" : " + dtoSegAutorizacion.getAuxComponenteNombre());
						
			// Identificar Grupos
			SeguridadMenuItem mnuGrp = lstEncontrados.stream()
				.filter(u -> u.getAplicacioncodigo().equals(dtoSegAutorizacion.getAplicacionId()))
				.filter(u -> u.getGrupoCodigo().equals(dtoSegAutorizacion.getContenedorWebGrupo()))//MENUV2
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
