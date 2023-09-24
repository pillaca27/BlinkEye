package net.royal.spring.seguridad.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.FotoConfiguracion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenu;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenuItem;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.seguridad.dominio.Seguridadperfilusuario;
import net.royal.spring.seguridad.dominio.Usuario;
import net.royal.spring.seguridad.dominio.UsuarioPk;
import net.royal.spring.seguridad.dominio.dto.DtoAutorizacion;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;

@Repository
public class UsuarioDaoImpl extends GenericoDaoImpl<Usuario, UsuarioPk> {

	private static Logger logger = LogManager.getLogger(Usuario.class);

	public UsuarioDaoImpl() {
		super("usuario");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Usuario obtenerPorId(String pusuario) {
		return obtenerPorId(new UsuarioPk(pusuario));
	}

	public Usuario coreInsertar(Usuario bean) {
		this.registrar(bean);
		return bean;
	}

	public Usuario coreInsertar(SeguridadUsuarioActual usuarioActual, Usuario bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public Usuario coreActualizar(SeguridadUsuarioActual usuarioActual, Usuario bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public Usuario coreActualizar(Usuario bean) {
		this.actualizar(bean);
		return bean;
	}

	public SeguridadMenu obtenerMenu(Integer tipo, String idAplicacion, String idUsuario) throws Exception {
		logger.debug("obtenerMenu2:" + tipo.toString());
		idUsuario = UString.obtenerValorCadenaSinNulo(idUsuario).toUpperCase();

		List<DtoAutorizacion> items = new ArrayList<>();

		if (tipo == 1) {
			logger.debug(idUsuario);
			items = listarAutorizacionPorUsuario(idUsuario);
		}
		if (tipo == 2) {
			logger.debug(idUsuario);
			logger.debug(idAplicacion);
			items = listarAutorizacionPorAplicacionUsuario(idAplicacion, idUsuario);
		}
		logger.debug("items.size():" + items.size());

		SeguridadMenu menu = new SeguridadMenu();
		menu.setItems(new ArrayList<>());

		Boolean flgEncontrado = Boolean.FALSE;
		List<String> lstAplicacion = new ArrayList();
		HashMap<String, SeguridadMenuItem> mapAplicacion = new HashMap<String, SeguridadMenuItem>();

		HashMap<String, SeguridadMenuItem> mapGrupo = new HashMap<String, SeguridadMenuItem>();
		List<String> lstGrupo = new ArrayList();

		for (DtoAutorizacion dto : items) {
			// CONCEPTOS
			String idConceptoTemp = dto.getAplicacioncodigo() + dto.getGrupo() + dto.getConcepto();
			SeguridadMenuItem menuConcepto = new SeguridadMenuItem();
			menuConcepto.setId(idConceptoTemp);
			menuConcepto.setLabel(dto.getConceptoNombre());
			menuConcepto.setRouterLink(dto.getWebpage());
			menuConcepto.setAction(dto.getWebaction());
			menuConcepto.setTipoDeAcceso(dto.getTipoDeAcceso());

			// menuConcepto.setFlgAgregar(dto.getFlgAgregar());
			// menuConcepto.setFlgModificar(dto.getFlgModificar());
			// menuConcepto.setFlgBorrar(dto.getFlgBorrar());
			menuConcepto.setFlgAgregar(Boolean.TRUE);
			menuConcepto.setFlgModificar(Boolean.TRUE);
			menuConcepto.setFlgBorrar(Boolean.TRUE);

			// menuConcepto.setFlgAprobar(dto.getFlgAprobar());
			// menuConcepto.setUrlAyuda(dto.getRutaayuda());

			if (UString.esNuloVacio(dto.getWebpage())) {
				menuConcepto.setIcon("fa fa-times");
			} else {
				if (UString.esNuloVacio(dto.getImagen()))
					menuConcepto.setIcon("fa fa-braille");
				else
					menuConcepto.setIcon(dto.getImagen());
			}

			// GRUPOS
			String idGrupoTemp = dto.getAplicacioncodigo() + dto.getGrupo();
			SeguridadMenuItem menuGrupo = mapGrupo.get(idGrupoTemp);
			if (menuGrupo == null) {
				menuGrupo = new SeguridadMenuItem();
				menuGrupo.setId(idGrupoTemp);
				menuGrupo.setLabel(dto.getGrupoNombre());
				if (UString.esNuloVacio(dto.getGrupoImagen()))
					menuGrupo.setIcon("fa fa-cubes");
				else
					menuGrupo.setIcon(dto.getGrupoImagen());
				menuGrupo.setItems(new ArrayList<>());
				lstGrupo.add(idGrupoTemp);
			}
			menuGrupo.getItems().add(menuConcepto);
			mapGrupo.put(idGrupoTemp, menuGrupo);

			// APLICACIONES
			SeguridadMenuItem menuApplicacion = mapAplicacion.get(dto.getAplicacioncodigo());
			if (menuApplicacion == null) {
				menuApplicacion = new SeguridadMenuItem();
				menuApplicacion.setOrden(dto.getAplicacionOrden());
				menuApplicacion.setId(dto.getAplicacioncodigo());
				menuApplicacion.setLabel(dto.getAplicacionNombre());
				if (UString.esNuloVacio(dto.getAplicacionImagen()))
					menuApplicacion.setIcon("fa fa-th-list");
				else
					menuApplicacion.setIcon(dto.getAplicacionImagen());
				menuApplicacion.setItems(new ArrayList<>());
				logger.debug("aplicacion:" + menuApplicacion.getId() + " - orden:"
						+ menuApplicacion.getOrden().toString() + " - " + menuApplicacion.getLabel());
				mapAplicacion.put(dto.getAplicacioncodigo(), menuApplicacion);
				lstAplicacion.add(dto.getAplicacioncodigo());
			}
		}

		for (String grupocodigo : lstGrupo) {
			String aplicacion = grupocodigo.substring(0, 2);
			SeguridadMenuItem menuApplicacion = mapAplicacion.get(aplicacion);
			SeguridadMenuItem menuGrupo = mapGrupo.get(grupocodigo);
			menuApplicacion.getItems().add(menuGrupo);
			mapAplicacion.remove(aplicacion);
			mapAplicacion.put(aplicacion, menuApplicacion);
		}

		logger.debug("Listado de Aplicaciones");
		for (String appcodigo : lstAplicacion) {
			SeguridadMenuItem menuApplicacion = mapAplicacion.get(appcodigo);
			logger.debug("aplicacion:" + menuApplicacion.getId() + " - orden:" + menuApplicacion.getOrden().toString()
					+ " - " + menuApplicacion.getLabel());
			menu.getItems().add(menuApplicacion);
		}

		return menu;
	}

	public List<DtoAutorizacion> listarAutorizacionPorUsuario(String usuario) throws Exception {
		List<DominioParametroPersistencia> parameters = new ArrayList<DominioParametroPersistencia>();
		parameters.add(new DominioParametroPersistencia("par_usuario", String.class, usuario));
		List lista = listarPorQuery(DtoAutorizacion.class, "usuario.listarAutorizacionPorUsuario", parameters);
		return lista;
	}

	public List<DtoAutorizacion> listarAutorizacionPorAplicacionUsuario(String idAplicacion, String usuario)
			throws Exception {
		List<DominioParametroPersistencia> parameters = new ArrayList<DominioParametroPersistencia>();
		parameters.add(new DominioParametroPersistencia("par_aplicacion", String.class, idAplicacion));
		parameters.add(new DominioParametroPersistencia("par_usuario", String.class, usuario));
		List lista = listarPorQuery(DtoAutorizacion.class, "usuario.listarAutorizacionPorAplicacionUsuario",
				parameters);
		return lista;
	}
	
	public FotoConfiguracion obtenerParametroFotos()throws Exception {
		FotoConfiguracion cfg=new FotoConfiguracion();
		List lista = listarPorQuery(FotoConfiguracion.class, "usuario.obtenerParametroFotos");
		if (lista.size()==1) {
			FotoConfiguracion r1=(FotoConfiguracion)lista.get(0);
			cfg.setExtension(UString.obtenerValorCadenaSinNulo(r1.getExtension()).trim());
			cfg.setRuta(UString.obtenerValorCadenaSinNulo(r1.getRuta()).trim());
			cfg.setRutaweb(UString.obtenerValorCadenaSinNulo(r1.getRutaweb()).trim());
			cfg.setTipo(UString.obtenerValorCadenaSinNulo(r1.getTipo()).trim());
			return cfg;
		}
		return cfg;
	}

	@SuppressWarnings("rawtypes")
	public List obtenerParametroCorreo() throws Exception {
		List lista = listarPorQuery(DtoTabla.class, "usuario.obtenerParametroCorreo");
		return lista;
	}

	public Usuario obtenerPorUsuarioRed(String idUsuarioRed) {
		Criteria criteria = getCriteria();
		criteria = criteria.add(Restrictions.eq("usuariored", idUsuarioRed));
		List ls = criteria.list();
		if (ls.size() != 1) {
			return null;
		}
		return (Usuario) ls.get(0);
	}
	public String usuarioEsAdministradorDeAplicacion(String aplicacionId,String usuarioId) throws Exception {
		List<DominioParametroPersistencia> parameters = new ArrayList<DominioParametroPersistencia>();
		parameters.add(new DominioParametroPersistencia("p_aplicacion", String.class, aplicacionId));
		parameters.add(new DominioParametroPersistencia("p_usuario", String.class, usuarioId));
		List lista = listarPorQuery(DtoTabla.class, "usuario.usuarioEsAdministradorDeAplicacion", parameters);
		logger.debug("EsAdministradorDeAplicacion:" + lista.size());
		if (lista.size()>0)
			return "S";
		return "N";
	}
	
	/* NUEVO MENU CORE */
	public List<DtoSegAutorizacion> listarDtoAutorizacionesAplicacionesUsuarioPerfilAdministrador(String usuario) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		logger.debug(usuario);
		parametros.add(new DominioParametroPersistencia("p_usuarioid", String.class, usuario));
		List datos = this.listarPorQuery(DtoSegAutorizacion.class, "usuario.listarDtoAutorizacionesAplicacionesUsuarioPerfilAdministrador", parametros);
		return datos;
	}
	public List<DtoSegAutorizacion> listarDtoAutorizacionesPorUsuario(String aplicacion,String usuario) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(aplicacion))
			aplicacion = null;
		parametros.add(new DominioParametroPersistencia("p_aplicacionid", String.class, aplicacion));
		parametros.add(new DominioParametroPersistencia("p_usuarioid", String.class, usuario));
		List datos = this.listarPorQuery(DtoSegAutorizacion.class, "usuario.listarDtoAutorizacionesPorUsuario", parametros);
		return datos;
	}
	public List<DtoSegAutorizacion> listarDtoAutorizacionesPorAdministrador(String aplicacion) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(aplicacion))
			aplicacion = null;
		parametros.add(new DominioParametroPersistencia("p_aplicacionid", String.class, aplicacion));
		List datos = this.listarPorQuery(DtoSegAutorizacion.class, "usuario.listarDtoAutorizacionesPorAdministrador", parametros);
		return datos;
	}
	public List<DtoSegAutorizacion> listarDtoAutorizacionesGruposPorAplicacion(String aplicacion) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(aplicacion))
			aplicacion = null;
		parametros.add(new DominioParametroPersistencia("p_aplicacionid", String.class, aplicacion));
		List datos = this.listarPorQuery(DtoSegAutorizacion.class, "usuario.listarDtoAutorizacionesGruposPorAplicacion", parametros);
		return datos;
	}
	public List<DtoSegAutorizacion> listarDtoAutorizacionesPorUsuarioTodos(String aplicacion,String usuario,List<Seguridadperfilusuario> lstpu) throws Exception {
		List<DtoSegAutorizacion> lstTodos = new ArrayList<DtoSegAutorizacion>();
		List<DtoSegAutorizacion> lstPorUsuario = new ArrayList<DtoSegAutorizacion>();
		List<DtoSegAutorizacion> lstPorPerfil = new ArrayList<DtoSegAutorizacion>();
		
		String esAdmin = usuarioEsAdministradorDeAplicacion(aplicacion,usuario);
		
		if (esAdmin.equals("S")) {
			lstPorUsuario = this.listarDtoAutorizacionesPorAdministrador(aplicacion);
		}else {
			lstPorUsuario = this.listarDtoAutorizacionesPorUsuario(aplicacion,usuario);
			
			if (lstpu.size()>0)
				lstPorPerfil = this.listarDtoAutorizacionesPorUsuario(aplicacion,lstpu.get(0).getPk().getPerfil());
		}
		
		if ((lstPorUsuario.size()==0) && (lstPorPerfil.size()==0)) {
			logger.debug("no se encontraron opciones de menu");
			return lstTodos;
		}
		
		if ((lstPorUsuario.size()>0) && (lstPorPerfil.size()>0)) {
			logger.debug("merge de las 2 listas");
			lstTodos = new ArrayList<DtoSegAutorizacion>();
			lstTodos.addAll(lstPorUsuario);
			for (DtoSegAutorizacion dto : lstPorPerfil) {
				DtoSegAutorizacion find = lstTodos.stream()
						.filter(u -> u.getAplicacionId().equals(dto.getAplicacionId()))
						.filter(u -> u.getContenedorId().equals(dto.getContenedorId()))
						.filter(u -> u.getComponenteId().equals(dto.getComponenteId()))
						.findFirst().orElse(null);
				if (find==null)
					lstTodos.add(dto);
			}
		}else if(lstPorUsuario.size()>0){
			logger.debug("solo usuarios");
			lstTodos = lstPorUsuario; 
		}else {
			logger.debug("solo perfiles");
			lstTodos = lstPorPerfil;
		}
		return lstTodos;
	}
	public SeguridadMenuItem crearAplicacion(DtoSegAutorizacion segComp) {
		SeguridadMenuItem itemC=new SeguridadMenuItem();
		String id = segComp.getAplicacionId();
		//itemC.setSidId(segComp.getSidId());
		itemC.setId(id);
		itemC.setLabel(segComp.getAuxAplicacionNombre());
		itemC.setIcon(segComp.getAuxAplicacionImagen());
		itemC.setOrden(segComp.getAuxAplicacionOrden());
		
		itemC.setAplicacioncodigo(segComp.getAplicacionId());		
		itemC.setAplicacionNombre(segComp.getAuxAplicacionNombre());
		itemC.setAplicacionOrden(segComp.getAuxAplicacionOrden());
		itemC.setAplicacionImagen(segComp.getAuxAplicacionImagen());
		return itemC;
	}
	public SeguridadMenuItem crearGrupoDesdeContenedor(DtoSegAutorizacion segComp) {
		SeguridadMenuItem itemC=new SeguridadMenuItem();
		String id = segComp.getAplicacionId() + segComp.getContenedorId();
		//itemC.setSidId(segComp.getSidId());
		itemC.setId(id);
		itemC.setLabel(segComp.getAuxContenedorNombre());
		itemC.setIcon(segComp.getAuxContenedorImagen());
		itemC.setOrden(segComp.getAuxContenedorOrden());
		
		itemC.setAplicacioncodigo(segComp.getAplicacionId());		
		itemC.setAplicacionNombre(segComp.getAuxAplicacionNombre());
		itemC.setAplicacionOrden(segComp.getAuxAplicacionOrden());
		itemC.setAplicacionImagen(segComp.getAuxAplicacionImagen());
		
		itemC.setGrupoCodigo(segComp.getContenedorId());
		itemC.setGrupoNombre(segComp.getAuxContenedorNombre());
		itemC.setGrupoOrden(segComp.getAuxContenedorOrden());
		itemC.setGrupoImagen(segComp.getAuxContenedorImagen());
		
		itemC.setGrupoPadreCodigo(segComp.getAuxContenedorPadreId());
		itemC.setGrupoPadreNombre(segComp.getAuxContenedorPadreNombre());
		itemC.setGrupoPadreOrden(segComp.getAuxContenedorPadreOrden());
		itemC.setGrupoPadreImagen(segComp.getAuxContenedorPadreImagen());
		return itemC;
	}
	public SeguridadMenuItem crearMenuDesdeComponente(DtoSegAutorizacion com) {
		SeguridadMenuItem itemC=new SeguridadMenuItem();
		String id = com.getAplicacionId() + com.getContenedorId() + com.getComponenteId();
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
		
		itemC.setGrupoCodigo(com.getContenedorId());
		itemC.setGrupoNombre(com.getAuxContenedorNombre());
		itemC.setGrupoOrden(com.getAuxContenedorOrden());
		itemC.setGrupoImagen(com.getAuxContenedorImagen());
		
		itemC.setGrupoPadreCodigo(com.getAuxContenedorPadreId());
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
		String grupoId = c.getAplicacionId()+c.getContenedorId();
		logger.debug("	GRUPO ID : "+grupoId);
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

	public List armarArbol(List<SeguridadMenuItem> lstEncontrados,List<SeguridadMenuItem> lstGrupos,
			List<DtoSegAutorizacion> lstGruposTodos,DtoSegAutorizacion mnuGrp1) {
	
	List<SeguridadMenuItem> lstTemporal=new ArrayList<SeguridadMenuItem>();
	List lst=new ArrayList<Object>();
	Boolean flgProcesar=Boolean.TRUE;
	
	if (UString.esNuloVacio(mnuGrp1.getAuxContenedorPadreId())) {
		String id = mnuGrp1.getAplicacionId() + mnuGrp1.getContenedorId();
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
			.filter(u -> u.getContenedorId().equals(mnuGrp1.getAuxContenedorPadreId()))
			.findFirst().orElse(null);		
	lstTemporal.add(0,this.crearGrupoDesdeContenedor(mnuGrp2));
	
	if (!UString.esNuloVacio(mnuGrp2.getAuxContenedorPadreId())) {
		
		DtoSegAutorizacion mnuGrp3 = lstGruposTodos.stream()
				.filter(u -> u.getAplicacionId().equals(mnuGrp2.getAplicacionId()))
				.filter(u -> u.getContenedorId().equals(mnuGrp2.getAuxContenedorPadreId()))
				.findFirst().orElse(null);			
		lstTemporal.add(0,this.crearGrupoDesdeContenedor(mnuGrp3));
	
		if (!UString.esNuloVacio(mnuGrp3.getAuxContenedorPadreId())) {	
			
			DtoSegAutorizacion mnuGrp4 = lstGruposTodos.stream()
					.filter(u -> u.getAplicacionId().equals(mnuGrp3.getAplicacionId()))
					.filter(u -> u.getContenedorId().equals(mnuGrp3.getAuxContenedorPadreId()))
					.findFirst().orElse(null);			
			lstTemporal.add(0,this.crearGrupoDesdeContenedor(mnuGrp4));
			
			if (!UString.esNuloVacio(mnuGrp4.getAuxContenedorPadreId())) {	
				
				DtoSegAutorizacion mnuGrp5 = lstGruposTodos.stream()
						.filter(u -> u.getAplicacionId().equals(mnuGrp4.getAplicacionId()))
						.filter(u -> u.getContenedorId().equals(mnuGrp4.getAuxContenedorPadreId()))
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

}
