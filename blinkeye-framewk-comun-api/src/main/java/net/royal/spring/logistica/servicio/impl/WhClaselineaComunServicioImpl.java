package net.royal.spring.logistica.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.logistica.dao.impl.WhClaselineaComunDaoImpl;
import net.royal.spring.util.dominio.dto.DtoComunArbol;

@Service(value = "WhClaselineaComunServicioImpl")
public class WhClaselineaComunServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioComunWhClaselinea";
	private static Logger logger = LogManager.getLogger(WhClaselineaComunServicioImpl.class);
	
	
	@Autowired
	private WhClaselineaComunDaoImpl whClaselineaDao;
	
	
	public List<DtoComunArbol> cargarTree() {
		List<DtoComunArbol> familia = this.DwMaWhLineafamiliaSubRetrieve();
		List<DtoComunArbol> listaResultado = new ArrayList<DtoComunArbol>();
		Integer v_linea = 0;

		for (DtoComunArbol objHijo : familia) {
			if (Integer.parseInt(objHijo.getLinea()) > v_linea) {
				objHijo.setExpandedIcon("pi pi-folder-open");
				objHijo.setCollapsedIcon("pi pi-folder");
				objHijo.setLabel(objHijo.getLineaCompleta());
				listaResultado.add(objHijo);

				v_linea = Integer.parseInt(objHijo.getLinea());
				cargarSegundoNivel(objHijo);
			}
		}
		return listaResultado;
	}
	

	public List DwMaWhLineafamiliaSubRetrieve() {
		List datos = whClaselineaDao.listarPorQuery(DtoComunArbol.class, "whclaselinea.DwMaWhLineafamiliaSubRetrieve", null);
		return datos;
	}
	
	private void cargarSegundoNivel(DtoComunArbol obj) {
		List<DtoComunArbol> lstUbicacionesHijas = filtrarSubCriteriosHijos(Integer.parseInt(obj.getLinea()));
		if (!UValidador.esListaVacia(lstUbicacionesHijas)) {
			obj.getChildren().addAll(lstUbicacionesHijas);
		}
	}
	
	private List filtrarSubCriteriosHijos(Integer lineaNumber) {
		List<DtoComunArbol> lstUbicacionHijoTemp = new ArrayList<DtoComunArbol>();
		List<DtoComunArbol> listaResultado = new ArrayList<DtoComunArbol>();
		lstUbicacionHijoTemp = this.DwMaWhLineafamiliaSubRetrieveFiltro2(lineaNumber);
		Integer v_familia = 0;
		for (DtoComunArbol objHijo : lstUbicacionHijoTemp) {
			if (Integer.parseInt(objHijo.getFamilia()) > v_familia) {
				objHijo.setExpandedIcon("pi pi-folder-open");
				objHijo.setCollapsedIcon("pi pi-folder");
				listaResultado.add(objHijo);
				v_familia = Integer.parseInt(objHijo.getFamilia());
				cargarTercerNivel(objHijo);
			}
		}
		return listaResultado;
	}
	
	public List DwMaWhLineafamiliaSubRetrieveFiltro2(Integer lineaNumber) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("lineaNumber", Integer.class, lineaNumber));
		List datos = whClaselineaDao.listarPorQuery(DtoComunArbol.class, "whclaselinea.DwMaWhLineafamiliaSubRetrieveFiltro2", parametros);
		return datos;
	}
	
	private void cargarTercerNivel(DtoComunArbol obj) {
		List<DtoComunArbol> lstUbicacionesHijas = filtrarSubCriteriosNietos(Integer.parseInt(obj.getLinea()),
				Integer.parseInt(obj.getFamilia()));
		if (!UValidador.esListaVacia(lstUbicacionesHijas)) {
			obj.getChildren().addAll(lstUbicacionesHijas);
		}
	}
	
	private List filtrarSubCriteriosNietos(Integer lineaNumber, Integer familiaNumber) {
		List lstUbicacionHijoTemp = new ArrayList<Class>();
		lstUbicacionHijoTemp = this.DwMaWhLineafamiliaSubRetrieveFiltro3(lineaNumber, familiaNumber);
		return lstUbicacionHijoTemp;
	}
	
	public List DwMaWhLineafamiliaSubRetrieveFiltro3(Integer lineaNumber, Integer familiaNumber) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("lineaNumber", Integer.class, lineaNumber));
		parametros.add(new DominioParametroPersistencia("familiaNumber", Integer.class, familiaNumber));
		List datos = whClaselineaDao.listarPorQuery(DtoComunArbol.class, "whclaselinea.DwMaWhLineafamiliaSubRetrieveFiltro3", parametros);
		return datos;
	}
	
	
	public List<DtoComunArbol> cargarTreeServicios() {
		List<DtoComunArbol> tipo = this.obtenerTipoServicio();
		List<DtoComunArbol> listaResultado = new ArrayList<DtoComunArbol>();
		for (DtoComunArbol objHijo : tipo) {
				objHijo.setExpandedIcon("pi pi-folder-open");
				objHijo.setCollapsedIcon("pi pi-folder");
				listaResultado.add(objHijo);
				cargarPrimerNivel(objHijo,objHijo.getGrupolinea());
		}
		return listaResultado;
	}
	
	public List obtenerTipoServicio() {
		List datos = whClaselineaDao.listarPorQuery(DtoComunArbol.class, "whclaselinea.obtenerTipoServicio", null);
		return datos;
	}
	
	public List<DtoComunArbol> cargarPrimerNivel(DtoComunArbol obj,String grupolinea) {
		List<DtoComunArbol> familia = this.DwMaWhCommodityLineaFamiliaSubSelect(grupolinea);
		List<DtoComunArbol> listaResultado = new ArrayList<DtoComunArbol>();
		Integer v_linea = 0;
		for (DtoComunArbol objHijo : familia) {
			if (Integer.parseInt(objHijo.getLinea()) > v_linea) {
				objHijo.setExpandedIcon("pi pi-folder-open");
				objHijo.setCollapsedIcon("pi pi-folder");
				objHijo.setLabel(objHijo.getLinea() + "-"+ objHijo.getLineadescripcion());
				obj.getChildren().add(objHijo);
				//listaResultado.add(objHijo);
				v_linea = Integer.parseInt(objHijo.getLinea());
				cargarSegundoNivelServicios(objHijo,grupolinea);
			}
		}
		return listaResultado;
	}
	
	public List DwMaWhCommodityLineaFamiliaSubSelect(String grupolinea) {
		List<DominioParametroPersistencia> filtros = new ArrayList<DominioParametroPersistencia>();
		filtros.add(new DominioParametroPersistencia("p_grupolinea", String.class, grupolinea));
		return whClaselineaDao.listarPorQuery(DtoComunArbol.class, "whclaselinea.DwMaWhCommodityLineaFamiliaSubSelect",
				filtros);
	}
	
	private void cargarSegundoNivelServicios(DtoComunArbol obj,String grupolinea) {
		List<DtoComunArbol> lstUbicacionesHijas = filtrarSubCriteriosHijosServicios(obj.getLinea(),grupolinea);
		if (!UValidador.esListaVacia(lstUbicacionesHijas)) {
			obj.getChildren().addAll(lstUbicacionesHijas);
		}
	}
	
	private List filtrarSubCriteriosHijosServicios(String lineaNumber,String grupolinea) {
		List<DtoComunArbol> lstUbicacionHijoTemp = new ArrayList<DtoComunArbol>();
		List<DtoComunArbol> listaResultado = new ArrayList<DtoComunArbol>();
		lstUbicacionHijoTemp = this.DwMaWhCommodityLineaFamiliaSubSelect2(grupolinea,lineaNumber);
		Integer v_linea = 0;
		for (DtoComunArbol objHijo : lstUbicacionHijoTemp) {
			if (Integer.parseInt(objHijo.getFamilia()) > v_linea) {
				objHijo.setExpandedIcon("pi pi-folder-open");
				objHijo.setCollapsedIcon("pi pi-folder");
				objHijo.setLabel(objHijo.getFamilia() + "-"+  objHijo.getFamiliadescripcion());
				listaResultado.add(objHijo);
				v_linea = Integer.parseInt(objHijo.getFamilia());
				cargarTercerNivelServicios(objHijo,grupolinea);
			}
		}
		return listaResultado;
	}
	
	public List DwMaWhCommodityLineaFamiliaSubSelect2(String grupolinea, String linea) {

		List<DominioParametroPersistencia> filtros = new ArrayList<DominioParametroPersistencia>();
		filtros.add(new DominioParametroPersistencia("p_grupolinea", String.class, grupolinea));
		filtros.add(new DominioParametroPersistencia("p_linea", String.class, linea));

		return whClaselineaDao.listarPorQuery(DtoComunArbol.class, "whclaselinea.DwMaWhCommodityLineaFamiliaSubSelect2",
				filtros);
	}
		
	private void cargarTercerNivelServicios(DtoComunArbol obj,String grupolinea) {
		List<DtoComunArbol> lstUbicacionesHijas = filtrarSubCriteriosNietosServicios(obj.getLinea(), obj.getFamilia(),grupolinea);
		if (!UValidador.esListaVacia(lstUbicacionesHijas)) {
			obj.getChildren().addAll(lstUbicacionesHijas);
		}
	}
	
	private List filtrarSubCriteriosNietosServicios(String lineaNumber, String familiaNumber,String grupolinea) {
		List lstUbicacionHijoTemp = new ArrayList<Class>();
		lstUbicacionHijoTemp = this.DwMaWhCommodityLineaFamiliaSubSelect3(grupolinea,lineaNumber, familiaNumber);
		return lstUbicacionHijoTemp;
	}
	
	public List DwMaWhCommodityLineaFamiliaSubSelect3(String grupolinea, String linea, String familia) {

		List<DominioParametroPersistencia> filtros = new ArrayList<DominioParametroPersistencia>();
		filtros.add(new DominioParametroPersistencia("p_grupolinea", String.class, grupolinea));
		filtros.add(new DominioParametroPersistencia("p_linea", String.class, linea));
		filtros.add(new DominioParametroPersistencia("p_familia", String.class, familia));
		return whClaselineaDao.listarPorQuery(DtoComunArbol.class, "whclaselinea.DwMaWhCommodityLineaFamiliaSubSelect3", filtros);
	}
	
}
