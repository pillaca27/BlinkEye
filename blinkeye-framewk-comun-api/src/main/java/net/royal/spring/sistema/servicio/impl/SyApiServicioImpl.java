package net.royal.spring.sistema.servicio.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyApiDaoImpl;
import net.royal.spring.sistema.dao.impl.SyApipathDaoImpl;
import net.royal.spring.sistema.dao.impl.SyDefinicionDaoImpl;
import net.royal.spring.sistema.dao.impl.SyDefinicionpropiedadDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyApi;
import net.royal.spring.sistema.dominio.BeanSyApiPk;
import net.royal.spring.sistema.dominio.BeanSyApipath;
import net.royal.spring.sistema.dominio.BeanSyApipathPk;
import net.royal.spring.sistema.dominio.BeanSyDefinicion;
import net.royal.spring.sistema.dominio.BeanSyDefinicionPk;
import net.royal.spring.sistema.dominio.BeanSyDefinicionpropiedad;
import net.royal.spring.sistema.dominio.dto.DtoComunWsApi;
import net.royal.spring.sistema.dominio.dto.DtoComunWsApipath;
import net.royal.spring.sistema.dominio.dto.DtoComunWsDefinicion;
import net.royal.spring.sistema.dominio.dto.DtoComunWsDefinicionpropiedad;
import net.royal.spring.sistema.dominio.filtro.FiltroComunApi;
import net.royal.spring.sistemas.api.impl.SyApiDefinicionApiImpl;
import net.royal.spring.sistemas.api.impl.SyApiPathApiImpl;

@Service
public class SyApiServicioImpl extends GenericoServicioImpl {

	private static Logger logger = LogManager.getLogger(SyApiServicioImpl.class);

	@Autowired
	private SyApiDaoImpl syApiDaoImpl;

	@Autowired
	private SyApipathDaoImpl syApipathDaoImpl;

	@Autowired
	private SyDefinicionDaoImpl syDefinicionDaoImpl;

	@Autowired
	private SyDefinicionpropiedadDaoImpl syDefinicionpropiedadDaoImpl;

	@Autowired
	private SyApiDefinicionApiImpl syApiDefinicionApiImpl;

	@Autowired
	private SyApiPathApiImpl syApiPathApiImpl;

	public List<DtoTabla> listarApis() {
		return syApiDaoImpl.listarApis();
	}

	public DominioPaginacion listarrutaspaginado(FiltroComunApi filtro) {
		return syApiDaoImpl.listarrutaspaginado(filtro);
	}

	public DominioTransaccion registrar(DtoComunWsApi dto) throws Exception {
		DominioTransaccion dominioTransaccion = new DominioTransaccion();

		BeanSyApi syApi = new BeanSyApi();
		syApi.getPk().setIdapi(syApiDaoImpl.generarId());
		syApi.setHost(dto.getHost());
		syApi.setNombre(dto.getNombre());
		syApi.setFecha(new Date());
		syApiDaoImpl.registrar(syApi);

		int totalDef = dto.getDefiniciones().size();

		for (DtoComunWsDefinicion row : dto.getDefiniciones()) {
			row.setIdApi(syApi.getPk().getIdapi());
			syApiDefinicionApiImpl.registrarDefinicion(row);
//			System.out.println("Def : " + row.getIdDefinicion().intValue());
		}

//		System.out.println("Definiciones : " + totalDef);

		Integer totalPath = dto.getPaths().size();

		int iPath = 1;
		for (DtoComunWsApipath row : dto.getPaths()) {
			row.setIdApi(syApi.getPk().getIdapi());
			row.setIdPath(iPath);
			syApiPathApiImpl.registrarPath(row);
//			System.out.println("Path : " + iPath);
			iPath++;
		}

//		System.out.println("Paths : " + totalPath);

		dominioTransaccion.setTransaccionEstado(DominioTransaccion.OK);
		return dominioTransaccion;
	}

	public DtoComunWsApipath obtenerDetallePath(DtoComunWsApipath dto) {

		BeanSyApipath bean = syApipathDaoImpl.obtenerPorId(new BeanSyApipathPk(dto.getIdPath(), dto.getIdApi()));
		dto.setNotas(bean.getNotas());
		dto.setTipodatoRequest(bean.getTipodatorequest());
		dto.setTipodatoResponse(bean.getTipodatoresponse());

		if (!UInteger.esCeroOrNulo(bean.getDefinicionrequestid())) {
			BeanSyDefinicion syDefinicion = syDefinicionDaoImpl
					.obtenerPorId(new BeanSyDefinicionPk(dto.getIdApi(), bean.getDefinicionrequestid()));
			DtoComunWsDefinicion dtoRequest = new DtoComunWsDefinicion();
			dtoRequest.setNombre(syDefinicion.getNombre());

			List<BeanSyDefinicionpropiedad> props = syDefinicionpropiedadDaoImpl.listarPorDefinicion(dto.getIdApi(),
					bean.getDefinicionrequestid());

			for (BeanSyDefinicionpropiedad syDefinicionpropiedad : props) {
				DtoComunWsDefinicionpropiedad prop = new DtoComunWsDefinicionpropiedad();
				prop.setNombre(syDefinicionpropiedad.getNombre());
				prop.setTipodato(syDefinicionpropiedad.getTipodato());
				prop.setIdDefinicion(syDefinicionpropiedad.getDefinicionpadre());
				dtoRequest.getPropiedades().add(prop);
			}

			dto.setDefinicionRequest(dtoRequest);
		}

		if (!UInteger.esCeroOrNulo(bean.getDefinicionresponseid())) {
			BeanSyDefinicion syDefinicion = syDefinicionDaoImpl
					.obtenerPorId(new BeanSyDefinicionPk(dto.getIdApi(), bean.getDefinicionresponseid()));
			DtoComunWsDefinicion dtoResponse = new DtoComunWsDefinicion();
			dtoResponse.setNombre(syDefinicion.getNombre());

			List<BeanSyDefinicionpropiedad> props = syDefinicionpropiedadDaoImpl.listarPorDefinicion(dto.getIdApi(),
					bean.getDefinicionresponseid());

			for (BeanSyDefinicionpropiedad syDefinicionpropiedad : props) {
				DtoComunWsDefinicionpropiedad prop = new DtoComunWsDefinicionpropiedad();
				prop.setNombre(syDefinicionpropiedad.getNombre());
				prop.setTipodato(syDefinicionpropiedad.getTipodato());
				prop.setIdDefinicion(syDefinicionpropiedad.getDefinicionpadre());
				dtoResponse.getPropiedades().add(prop);
			}

			dto.setDefinicionResponse(dtoResponse);
		}

		return dto;
	}

	public DtoComunWsDefinicion registrarDefinicion(DtoComunWsDefinicion dto) {
		BeanSyDefinicion syDefinicion = new BeanSyDefinicion();
		syDefinicion.getPk().setIdapi(dto.getIdApi());
		syDefinicion.getPk().setIddefinicion(dto.getIdDefinicion());
		syDefinicion.setNombre(dto.getNombre());
		syDefinicionDaoImpl.registrar(syDefinicion);

		int iProp = 1;
		for (DtoComunWsDefinicionpropiedad prop : dto.getPropiedades()) {
			BeanSyDefinicionpropiedad syDefinicionpropiedad = new BeanSyDefinicionpropiedad();
			syDefinicionpropiedad.getPk().setIdapi(dto.getIdApi());
			syDefinicionpropiedad.getPk().setIddefinicion(syDefinicion.getPk().getIddefinicion());
			syDefinicionpropiedad.getPk().setIdpropiedad(iProp);
			syDefinicionpropiedad.setNombre(prop.getNombre());
			syDefinicionpropiedad.setTipodato(prop.getTipodato());
			syDefinicionpropiedad.setDefinicionpadre(prop.getIdDefinicion());
			syDefinicionpropiedadDaoImpl.registrar(syDefinicionpropiedad);
			iProp++;
		}
		return new DtoComunWsDefinicion();
	}

	public DtoComunWsApipath registrarPath(DtoComunWsApipath row) {
		BeanSyApipath syApipath = new BeanSyApipath();
		syApipath.getPk().setIdapi(row.getIdApi());
		syApipath.getPk().setIdpath(row.getIdPath());
		syApipath.setNombre(row.getNombre());
		syApipath.setMetodo(row.getMetodo());
		syApipath.setNotas(row.getNotas());
		syApipath.setDefinicionrequestid(row.getIdDefinicionRequest());
		syApipath.setTipodatorequest(row.getTipodatoRequest());
		syApipath.setDefinicionresponseid(row.getIdDefinicionResponse());
		syApipath.setTipodatoresponse(row.getTipodatoResponse());
		syApipathDaoImpl.registrar(syApipath);
		return new DtoComunWsApipath();
	}

	public DtoComunWsApipath vistobueno(DtoComunWsApipath dto) {
		BeanSyApipath syApipath = syApipathDaoImpl.obtenerPorId(new BeanSyApipathPk(dto.getIdPath(), dto.getIdApi()));
		if (syApipath.getVb() == null) {
			syApipath.setVb("S");
		}
		else if (syApipath.getVb().trim().equals("N")) {
			syApipath.setVb("S");
		}
		else if (syApipath.getVb().trim().equals("S")) {
			syApipath.setVb("N");
		}
		syApipathDaoImpl.actualizar(syApipath);
		return dto;
	}

}
