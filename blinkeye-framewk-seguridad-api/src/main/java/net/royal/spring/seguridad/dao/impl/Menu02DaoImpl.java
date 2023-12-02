package net.royal.spring.seguridad.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenuItem;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.seguridad.dominio.Usuario;
import net.royal.spring.seguridad.dominio.UsuarioPk;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;

@Repository
public class Menu02DaoImpl extends GenericoDaoImpl<Usuario, UsuarioPk> {

	private static Logger logger = LogManager.getLogger(Usuario.class);

	public Menu02DaoImpl() {
		super("menu02");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	
	public SeguridadMenuItem crearGrupoDesdeContenedor(DtoSegAutorizacion segComp) {
		SeguridadMenuItem itemC=new SeguridadMenuItem();
		String id = segComp.getAplicacionId() + segComp.getContenedorWebGrupo(); // MENUV2

		itemC.setId(id);
		itemC.setLabel(segComp.getAuxContenedorNombre());
		itemC.setIcon(segComp.getAuxContenedorImagen());
		itemC.setOrden(segComp.getAuxContenedorOrden());
		
		itemC.setAplicacioncodigo(segComp.getAplicacionId());		
		itemC.setAplicacionNombre(segComp.getAuxAplicacionNombre());
		itemC.setAplicacionOrden(segComp.getAuxAplicacionOrden());
		itemC.setAplicacionImagen(segComp.getAuxAplicacionImagen());
		
		itemC.setGrupoCodigo(segComp.getContenedorWebGrupo());// MENUV2
		itemC.setGrupoNombre(segComp.getAuxContenedorNombre());
		itemC.setGrupoOrden(segComp.getAuxContenedorOrden());
		itemC.setGrupoImagen(segComp.getAuxContenedorImagen());
		
		itemC.setGrupoPadreCodigo(segComp.getAuxContenedorPadreWebGrupo()); // MENUV2
		itemC.setGrupoPadreNombre(segComp.getAuxContenedorPadreNombre());
		itemC.setGrupoPadreOrden(segComp.getAuxContenedorPadreOrden());
		itemC.setGrupoPadreImagen(segComp.getAuxContenedorPadreImagen());
		return itemC;
	}
	
	public SeguridadMenuItem crearMenuDesdeComponente(DtoSegAutorizacion com) {
		SeguridadMenuItem itemC=new SeguridadMenuItem();
		String id = com.getAplicacionId() + com.getContenedorWebGrupo() + com.getComponenteId(); // MENUV2
		//itemC.setSidId(com.getSidId());
		itemC.setId(id);
		itemC.setLabel(com.getAuxComponenteNombre());
		itemC.setIcon(com.getAuxComponenteImagen());
		itemC.setRouterLink(com.getAuxComponenteRutaWeb());
		itemC.setFlgAgregar(com.isAuxFlgNuevo());
		itemC.setFlgModificar(com.isAuxFlgEditar());
		itemC.setFlgBorrar(com.isAuxFlgEliminar());
		itemC.setFlgAprobar(com.isAuxFlgAprobar());
		/*itemC.setFlgVer(com.isAuxFlgVer());
		itemC.setFlgAnular(com.isAuxFlgAnular());
		itemC.setFlgImprimir(com.isAuxFlgImprimir());
		itemC.setFlgExportar(com.isAuxFlgExportar());
		itemC.setFlgOpcion1(com.isAuxFlgOpcion1());
		itemC.setFlgOpcion2(com.isAuxFlgOpcion2());
		itemC.setFlgOpcion3(com.isAuxFlgOpcion3());*/
		
		itemC.setAplicacioncodigo(com.getAplicacionId());		
		itemC.setAplicacionNombre(com.getAuxAplicacionNombre());
		itemC.setAplicacionOrden(com.getAuxAplicacionOrden());
		itemC.setAplicacionImagen(com.getAuxAplicacionImagen());
		
		itemC.setGrupoCodigo(com.getContenedorWebGrupo());// MENUV2
		itemC.setGrupoNombre(com.getAuxContenedorNombre());
		itemC.setGrupoOrden(com.getAuxContenedorOrden());
		itemC.setGrupoImagen(com.getAuxContenedorImagen());
		
		itemC.setGrupoPadreCodigo(com.getAuxContenedorPadreWebGrupo()); // MENUV2
		itemC.setGrupoPadreNombre(com.getAuxContenedorPadreNombre());
		itemC.setGrupoPadreOrden(com.getAuxContenedorPadreOrden());
		itemC.setGrupoPadreImagen(com.getAuxContenedorPadreImagen());
		
		return itemC;
	}
	
	public List<SeguridadMenuItem> asignarConcepto(List<SeguridadMenuItem> mnuLista,DtoSegAutorizacion c) {
		
		//logger.debug("==========================>");
		//logger.debug("getAplicacionId:"+c.getAplicacionId());
		//logger.debug("getContenedorId:"+c.getContenedorId());
		//logger.debug("getComponenteId:"+c.getComponenteId());
		String grupoId = c.getAplicacionId()+c.getContenedorWebGrupo();
		//logger.debug("	GRUPO ID : "+grupoId);
		Boolean grupoEncontrado=Boolean.FALSE;
		
		SeguridadMenuItem mnuComponente = this.crearMenuDesdeComponente(c);
		
		HashMap<String, Object> map = buscarMenu(mnuLista,grupoId,mnuComponente);
		grupoEncontrado = (Boolean)map.get("encontrado");
		mnuLista = (List<SeguridadMenuItem>)map.get("lista");
				
		return mnuLista;
	}
	
	public HashMap<String, Object> buscarMenu(List<SeguridadMenuItem> mnu,String grupoId,SeguridadMenuItem mnuComponente) {
		String tagEncontrado="encontrado";
		String tagLista="lista";
		HashMap<String, Object> map = new HashMap<>();
				
		for (int i = 0; i < mnu.size(); i++) {
			if (mnu.get(i).getId().equals(grupoId)) {
				if (mnu.get(i).getItems()==null)
					mnu.get(i).setItems(new ArrayList<SeguridadMenuItem>());
				mnu.get(i).getItems().add(mnuComponente);
				map.put(tagEncontrado, Boolean.TRUE);
				map.put(tagLista, mnu);
				return map;
			}
			if (mnu.get(i).getItems()==null)
				mnu.get(i).setItems(new ArrayList<SeguridadMenuItem>());
			for (int x = 0; x < mnu.get(i).getItems().size(); x++) {
				if (mnu.get(i).getItems().get(x).getId().equals(grupoId)) {
					mnu.get(i).getItems().get(x).getItems().add(mnuComponente);
					map.put(tagEncontrado, Boolean.TRUE);
					map.put(tagLista, mnu);
					return map;
				}	
				if (mnu.get(i).getItems().get(x).getItems()==null)
					mnu.get(i).getItems().get(x).setItems(new ArrayList<SeguridadMenuItem>());
				for (int z = 0; z < mnu.get(i).getItems().get(x).getItems().size(); z++) {
					if (mnu.get(i).getItems().get(x).getItems().get(z).getId().equals(grupoId)) {
						mnu.get(i).getItems().get(x).getItems().get(z).getItems().add(mnuComponente);
						map.put(tagEncontrado, Boolean.TRUE);
						map.put(tagLista, mnu);
						return map;
					}	
				}
			}
		}
				
		map.put(tagEncontrado, Boolean.FALSE);
		map.put(tagLista, mnu);
		return map;
	}
	
	public void pintarMenu(List<SeguridadMenuItem> mnuLista) {
		/***/
		logger.debug("Lista de Grupos");
		for (SeguridadMenuItem grp : mnuLista) {
			logger.debug("G=>" + grp.getLabel());
			/*if (grp.getItems().size()==0)
				grp.setItems(null);*/
			if (grp.getItems()==null)
				grp.setItems(new ArrayList<>());
			for (SeguridadMenuItem grp2 : grp.getItems()) {
				logger.debug("	G2=>" + grp2.getLabel());
				if (grp2.getItems()!=null) {
					for (SeguridadMenuItem grp3 : grp2.getItems()) {
						logger.debug("		G3=>" + grp3.getLabel());
						if (grp3.getItems()!=null) {
							for (SeguridadMenuItem grp4 : grp3.getItems()) {
								logger.debug("			G4=>" + grp4.getLabel());
								if (grp4.getItems()!=null) {
									for (SeguridadMenuItem grp5 : grp4.getItems()) {
										logger.debug("				G5=>" + grp5.getLabel());
										if (grp5.getItems()!=null) {
											for (SeguridadMenuItem grp6 : grp5.getItems()) {
												logger.debug("					G6=>" + grp6.getLabel());
												if (grp6.getItems()!=null) {
													for (SeguridadMenuItem grp7 : grp6.getItems()) {
														logger.debug("						G7=>" + grp7.getLabel());
													}	
												}									
											}	
										}								
									}
								}							
							}
						}					
					}
				}				
			}
		}
	}
	
	public List armarArbol(List<SeguridadMenuItem> lstEncontrados,List<SeguridadMenuItem> lstGrupos,
			List<DtoSegAutorizacion> lstGruposTodos,DtoSegAutorizacion mnuGrp1) {
	
	List<SeguridadMenuItem> lstTemporal=new ArrayList<SeguridadMenuItem>();
	List lst=new ArrayList<Object>();
	Boolean flgProcesar=Boolean.TRUE;
	
	if (UString.esNuloVacio(mnuGrp1.getAuxContenedorPadreWebGrupo())) {
		String id = mnuGrp1.getAplicacionId() + mnuGrp1.getContenedorWebGrupo(); // MENUV2 
		SeguridadMenuItem mnuGrpPadre = lstGrupos.stream()
				.filter(u -> u.getId().equals(id))
				.findFirst().orElse(null);	
		if (mnuGrpPadre==null) {
			lstEncontrados.add(this.crearGrupoDesdeContenedor(mnuGrp1));
			lstGrupos.add(this.crearGrupoDesdeContenedor(mnuGrp1));				
		}			
		lst.add(lstEncontrados);
		lst.add(lstGrupos);			
		return lst;
	}else {
		lstTemporal.add(this.crearGrupoDesdeContenedor(mnuGrp1));
		lstEncontrados.add(this.crearGrupoDesdeContenedor(mnuGrp1));
	}
	
	DtoSegAutorizacion mnuGrp2 = lstGruposTodos.stream()
			.filter(u -> u.getAplicacionId().equals(mnuGrp1.getAplicacionId()))
			.filter(u -> u.getContenedorWebGrupo().equals(mnuGrp1.getAuxContenedorPadreWebGrupo()))	// MENUV2
			.findFirst().orElse(null);		
	lstTemporal.add(0,this.crearGrupoDesdeContenedor(mnuGrp2));
	
	if (!UString.esNuloVacio(mnuGrp2.getAuxContenedorPadreWebGrupo())) {
		
		DtoSegAutorizacion mnuGrp3 = lstGruposTodos.stream()
				.filter(u -> u.getAplicacionId().equals(mnuGrp2.getAplicacionId()))
				.filter(u -> u.getContenedorWebGrupo().equals(mnuGrp2.getAuxContenedorPadreWebGrupo()))		// MENUV2
				.findFirst().orElse(null);			
		lstTemporal.add(0,this.crearGrupoDesdeContenedor(mnuGrp3));
	
		if (!UString.esNuloVacio(mnuGrp3.getAuxContenedorPadreWebGrupo())) {	
			
			DtoSegAutorizacion mnuGrp4 = lstGruposTodos.stream()
					.filter(u -> u.getAplicacionId().equals(mnuGrp3.getAplicacionId()))
					.filter(u -> u.getContenedorWebGrupo().equals(mnuGrp3.getAuxContenedorPadreWebGrupo()))	// MENUV2
					.findFirst().orElse(null);			
			lstTemporal.add(0,this.crearGrupoDesdeContenedor(mnuGrp4));
			
			if (!UString.esNuloVacio(mnuGrp4.getAuxContenedorPadreWebGrupo())) {	
				
				DtoSegAutorizacion mnuGrp5 = lstGruposTodos.stream()
						.filter(u -> u.getAplicacionId().equals(mnuGrp4.getAplicacionId()))
						.filter(u -> u.getContenedorWebGrupo().equals(mnuGrp4.getAuxContenedorPadreWebGrupo()))	// MENUV2
						.findFirst().orElse(null);			
				lstTemporal.add(0,this.crearGrupoDesdeContenedor(mnuGrp5));
			}
		}			
	}
	
	SeguridadMenuItem nivel1 = null;
	SeguridadMenuItem nivel2 = null;
	SeguridadMenuItem nivel3 = null;
	SeguridadMenuItem nivel4 = null;
	int index=0;
	for (SeguridadMenuItem o : lstTemporal) {
		if (index==0) {
			nivel1 = lstGrupos.stream()
					.filter(u -> u.getId().equals(o.getId()))
					.findFirst().orElse(null);
			if (nivel1==null) {
				nivel1 = lstTemporal.get(index);
				nivel1.setItems(new ArrayList<SeguridadMenuItem>());
				lstGrupos.add(nivel1);
			}					
		}
		if (index==1) {
			nivel2 = nivel1.getItems().stream()
					.filter(u -> u.getId().equals(o.getId()))
					.findFirst().orElse(null);	
			if (nivel2==null) {
				nivel2 = lstTemporal.get(index);
				nivel2.setItems(new ArrayList<SeguridadMenuItem>());
				nivel1.getItems().add(nivel2);
			}
		}
		if (index==2) {
			nivel3 = nivel2.getItems().stream()
					.filter(u -> u.getId().equals(o.getId()))
					.findFirst().orElse(null);
			if (nivel3==null) {
				nivel3 = lstTemporal.get(index);
				nivel3.setItems(new ArrayList<SeguridadMenuItem>());
				nivel2.getItems().add(nivel3);
			}
		}
		if (index==3) {
			nivel4 = nivel3.getItems().stream()
					.filter(u -> u.getId().equals(o.getId()))
					.findFirst().orElse(null);	
			if (nivel4==null) {
				nivel4 = lstTemporal.get(index);
				nivel4.setItems(new ArrayList<SeguridadMenuItem>());
				nivel3.getItems().add(nivel4);
			}
		}			
		index++;
	}
	
	lst.add(lstEncontrados);
	lst.add(lstGrupos);
	
	return lst;
	}
	
	public List<DtoSegAutorizacion> listarDtoAutorizacionesPorUsuarioTodos(String aplicacion,String usuario) throws Exception {
		List<DtoSegAutorizacion> lstTodos = new ArrayList<DtoSegAutorizacion>();
		List<DtoSegAutorizacion> lstConceptosPorUsuario = new ArrayList<DtoSegAutorizacion>();
		
		String esAdmin = usuarioEsAdministradorDeAplicacion(aplicacion,usuario);
		
		logger.debug("esAdmin:"+esAdmin);
		if (esAdmin.equals("S")) {
			lstConceptosPorUsuario = this.listarDtoAutorizacionesPorAdministrador(aplicacion);
			logger.debug("lstConceptosPorUsuario.size():"+lstConceptosPorUsuario.size());
			return lstConceptosPorUsuario; 
		}

		lstConceptosPorUsuario = this.listarDtoAutorizacionesDuplicadasPorUsuarioYPerfil(aplicacion,usuario);
		logger.debug("lstConceptosPorUsuario.size():"+lstConceptosPorUsuario.size());

		for (DtoSegAutorizacion dto : lstConceptosPorUsuario) {
			//logger.debug(dto.getAplicacionId()+"-"+dto.getContenedorWebGrupo()+"-"+dto.getComponenteWebGrupo());
			DtoSegAutorizacion find = lstTodos.stream()
					.filter(u -> u.getAplicacionId().equals(dto.getAplicacionId()))
					.filter(u -> u.getContenedorWebGrupo().equals(dto.getContenedorWebGrupo()))
					.filter(u -> u.getComponenteId().equals(dto.getComponenteId()))
					.findFirst().orElse(null);
			if (find==null) {
				lstTodos.add(dto);
			}else {
				if (UBoolean.validarFlag(dto.getFlgNuevo()))
					find.setFlgNuevo("S");
				
				if (UBoolean.validarFlag(dto.getFlgEditar()))
					find.setFlgEditar("S");
				
				if (UBoolean.validarFlag(dto.getFlgEliminar()))
					find.setFlgEliminar("S");
				
				if (UBoolean.validarFlag(dto.getFlgAprobar()))
					find.setFlgAprobar("S");
			}					
		}
		
		if ((lstConceptosPorUsuario.size()==0)) {
			logger.debug("no se encontraron opciones de menu");
			return lstTodos;
		}
		
		return lstTodos;
	}
	
	public String usuarioEsAdministradorDeAplicacion(String aplicacionId,String usuarioId) throws Exception {
		List<DominioParametroPersistencia> parameters = new ArrayList<DominioParametroPersistencia>();
		parameters.add(new DominioParametroPersistencia("p_aplicacion", String.class, aplicacionId));
		parameters.add(new DominioParametroPersistencia("p_usuario", String.class, usuarioId));
		List lista = listarPorQuery(DtoTabla.class, "menu02.usuarioEsAdministradorDeAplicacion", parameters);
		logger.debug("EsAdministradorDeAplicacion:" + lista.size());
		if (lista.size()>0)
			return "S";
		return "N";
	}
	public List<DtoSegAutorizacion> listarDtoAutorizacionesPorAdministrador(String aplicacion) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(aplicacion))
			aplicacion = null;
		parametros.add(new DominioParametroPersistencia("p_aplicacionid", String.class, aplicacion));
		List datos = this.listarPorQuery(DtoSegAutorizacion.class, "menu02.listarDtoAutorizacionesPorAdministrador", parametros);
		return datos;
	}
	public List<DtoSegAutorizacion> listarDtoAutorizacionesDuplicadasPorUsuarioYPerfil(String aplicacion,String usuario) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(aplicacion))
			aplicacion = null;
		parametros.add(new DominioParametroPersistencia("p_aplicacionid", String.class, aplicacion));
		parametros.add(new DominioParametroPersistencia("p_usuarioid", String.class, usuario));
		List datos = this.listarPorQuery(DtoSegAutorizacion.class, "menu02.listarDtoAutorizacionesDuplicadasPorUsuarioYPerfil", parametros);
		return datos;
	}
		
	public List<DtoSegAutorizacion> listarDtoAutorizacionesGruposPorAplicacion(String aplicacion) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(aplicacion))
			aplicacion = null;
		parametros.add(new DominioParametroPersistencia("p_aplicacionid", String.class, aplicacion));
		List datos = this.listarPorQuery(DtoSegAutorizacion.class, "menu02.listarDtoAutorizacionesGruposPorAplicacion", parametros);
		return datos;
	}
	
}
