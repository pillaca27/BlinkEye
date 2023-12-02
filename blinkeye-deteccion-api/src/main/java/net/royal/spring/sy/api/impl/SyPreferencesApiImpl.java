package net.royal.spring.sy.api.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.api.impl.GenericoApiImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunSyPreferences;

@Repository
public class SyPreferencesApiImpl extends GenericoApiImpl<DtoComunSyPreferences> {

	private static Logger logger = LogManager.getLogger(SyPreferencesApiImpl.class);

	public SyPreferencesApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun, "spring/sistema/sypreferencescomun",
				new ParameterizedTypeReference<List<DtoComunSyPreferences>>() {
				});
	}

	public String obtenerValorstring(String usuario, String preference) throws Exception {
		DtoComunSyPreferences obj = new DtoComunSyPreferences();
		obj.setUsuario(usuario);
		obj.setPreference(preference);

		return this.putToString("obtenervalorstring", obj);
	}
}
