package net.royal.spring.core.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.PersonamastWhDaoImpl;
import net.royal.spring.core.dominio.BeanPersonamast;
import net.royal.spring.core.dominio.BeanPersonamastPk;
import net.royal.spring.core.dominio.dto.DtoComunPersonaMast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarPersonamast")
public class PersonamastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarPersonamast";
	private static Logger logger = LogManager.getLogger(PersonamastServicioValidar.class);

	@Autowired
	private PersonamastWhDaoImpl personamastDao;

	private BeanPersonamast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast, Boolean flgInsertar) {
     personamast.setUltimousuario(usuarioActual.getUsuario());
     personamast.setUltimafechamodif(new Date());
		
		// TODO Personamast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return personamast;
	}

	public BeanPersonamast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast) {
		if (personamast == null)
			return personamast;
		if (personamast.getAuxFlgPreparadoBoolean())
			return personamast;
		personamast = prepararBasico(usuarioActual,personamast, true);
		personamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		personamast.setUuid(UUID.randomUUID().toString());
		// TODO Personamast.Insertar : valores por defecto
		
		return personamast;
	}

	public BeanPersonamast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast) {
		if (personamast == null)
			return personamast;
		if (personamast.getAuxFlgPreparadoBoolean())
			return personamast;
		personamast = prepararBasico(usuarioActual,personamast, false);
		personamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Personamast.Actualizar : valores por defecto
		
		return personamast;
	}

	public BeanPersonamast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast) {
		if (personamast == null)
			return personamast;
		if (personamast.getAuxFlgPreparadoBoolean())
			return personamast;
		personamast = prepararBasico(usuarioActual, personamast, false);
		personamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Personamast.Anular : valores por defecto
		
		return personamast;
	}

	public BeanPersonamast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast) {
		if (personamast == null)
			return personamast;
		if (personamast.getAuxFlgPreparadoBoolean())
			return personamast;
		personamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Personamast.Eliminar : valores por defecto
		
		return personamast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanPersonamast personamast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));

		
		if(UString.esNuloVacio(personamast.getTipodocumento())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_TIPO_DOC));
		}
		
		
		if (!lst.isEmpty())
			return lst;
		
		
		if(!UString.esNuloVacio(personamast.getBusqueda())) {

			
			if(personamast.getPk().getPersona() == null) {
				
				String resultado = this.personamastDao.obtenerBusqueda(personamast);
				
				if(resultado!= null) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_BUSQUEDA.concat(" : Nro. ").concat(resultado)));		
				}
			}else {
				DtoComunPersonaMast persona = this.personamastDao.obtenerPersona(personamast);
				
				if(!personamast.getBusqueda().equals(persona.getBusqueda())){
					String resultado = this.personamastDao.obtenerBusqueda(personamast);
					
					if(resultado!= null) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_BUSQUEDA.concat(" : Nro. ").concat(resultado)));		
					}
				}
			}
		}
		
		if(!UString.esNuloVacio(personamast.getDocumento())) {
			if(personamast.getPk().getPersona() == null) {
				String resultado = this.personamastDao.obtenerDocumento(personamast);
				
				if(resultado!= null) {
					if(personamast.getDocumento().length() == 11) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC.concat(" : Nro. ").concat(resultado)));
					}
					else if(personamast.getDocumento().length() == 8) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DNI.concat(" : Nro. ").concat(resultado)));
					}else {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO.concat(" : Nro. ").concat(resultado)));
					}
				}
			}
			else {
				DtoComunPersonaMast persona = this.personamastDao.obtenerPersona(personamast);
				
				if(!UString.esNuloVacio(persona.getDocumento())){
					if(!personamast.getDocumento().equals(persona.getDocumento().trim())){
						String resultado = this.personamastDao.obtenerDocumento(personamast);
						
						if(resultado!= null) {
							if(personamast.getDocumento().length() == 11) {
								lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC.concat(" : Nro. ").concat(resultado)));
							}
							else if(personamast.getDocumento().length() == 8) {
								lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DNI.concat(" : Nro. ").concat(resultado)));
							}else {
								lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO.concat(" : Nro. ").concat(resultado)));
							}
						}
					}else {
						System.out.println("VALID");
					}
				}

			}
		}
		
		
		if(!UString.esNuloVacio(personamast.getDocumentofiscal())) {
			if(personamast.getPk().getPersona() == null) {
				String resultado = this.personamastDao.obtenerDocumentoFiscal(personamast);
				
				if(resultado!= null) {
					if(personamast.getDocumentofiscal().length() == 11) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC.concat(" : Nro. ").concat(resultado)));
					}else {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO_FISCAL.concat(" : Nro. ").concat(resultado)));
					}					
				}
			}
			else {
				DtoComunPersonaMast persona = this.personamastDao.obtenerPersona(personamast);
				
				if(!UString.esNuloVacio(persona.getDocumentofiscal())) {
					if(!personamast.getDocumentofiscal().equals(persona.getDocumentofiscal().trim())){
						String resultado = this.personamastDao.obtenerDocumentoFiscal(personamast);
						
						if(resultado!= null) {
							if(personamast.getDocumentofiscal().length() == 11) {
								lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC.concat(" : Nro. ").concat(resultado)));
							}else {
								lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO_FISCAL.concat(" : Nro. ").concat(resultado)));
							}					
						}
					}else {
						System.out.println("VALID");
					}
				}

			}
		}
		
		if(!UString.esNuloVacio(personamast.getDocumentoidentidad())) {
			if(personamast.getPk().getPersona() == null) {
				String resultado = this.personamastDao.obtenerDocumentoIdentidad(personamast);
				
				if(resultado!= null) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO_IDENTIDAD.concat(" : Nro. ").concat(resultado)));

				}
			}
			else {
				DtoComunPersonaMast persona = this.personamastDao.obtenerPersona(personamast);
				
				if(!personamast.getDocumentoidentidad().equals(persona.getDocumentoidentidad().trim())){
					String resultado = this.personamastDao.obtenerDocumentoIdentidad(personamast);
					
					if(resultado!= null) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO_IDENTIDAD.concat(" : Nro. ").concat(resultado)));

					}
				}
			}
		}
		
		
		if (!lst.isEmpty())
			return lst;
		
		if(UString.esNuloVacio(personamast.getTipopersona())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_TIPO_PERSONA));
		}else {
			

			if(personamast.getTipopersona().equals("J")) {
				if(UString.esNuloVacio(personamast.getDocumento())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO));
				}
				if(UString.esNuloVacio(personamast.getTipodocumento())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_TIPO_DOC));
				}else {
					
					
					if(personamast.getTipodocumento().equals("R")) {
						
						
						if(UString.esNuloVacio(personamast.getDocumentofiscal())) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_FISCAL));
						}
						if(!personamast.getFlagRuc()) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC_VALID));
						}

						
					}
					else if(personamast.getTipodocumento().equals("D")) {
						if(UString.esNuloVacio(personamast.getDocumento())) {
							
						}else {
														
							if(personamast.getDocumento().trim().length() != 8 ) {
								lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DNI));
							}
						}
						
						if(UString.esNuloVacio(personamast.getDocumentoidentidad())) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_IDENTIDAD));
						}else {
														
							if(personamast.getDocumentoidentidad().trim().length() != 8 ) {
								lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO_IDENTIDAD));
							}
						}
					}
					
					
					
				}

				if (!lst.isEmpty())
					return lst;
			}
			else if(personamast.getTipopersona().equals("N")) {

				if(UString.esNuloVacio(personamast.getDocumento())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO));
				}
				
				if(UString.esNuloVacio(personamast.getDocumentoidentidad())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_IDENTIDAD));
				}

				if(personamast.getTipodocumento().equals("R")) {
					
					
					if(UString.esNuloVacio(personamast.getDocumentofiscal())) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_FISCAL));
					}
					if(!personamast.getFlagRuc()) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC_VALID));
					}

					
				}
				else if(personamast.getTipodocumento().equals("D")) {
					if(UString.esNuloVacio(personamast.getDocumento())) {
						//lst.add(this.getMsjUsuarioError(SpringLogisticoConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO));
					}else {
													
						if(personamast.getDocumento().trim().length() != 8 ) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DNI));
						}
					}
					
					if(UString.esNuloVacio(personamast.getDocumentoidentidad())) {
						
					}else {
													
						if(personamast.getDocumentoidentidad().trim().length() != 8 ) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO_IDENTIDAD));
						}
					}
				}
				
				
				

				if(UString.esNuloVacio(personamast.getApellidopaterno())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_APE_PATERNO));
				}
				if(UString.esNuloVacio(personamast.getApellidomaterno())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_APE_MATERNO));
				}
				if(UString.esNuloVacio(personamast.getNombres())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_NOMBRES));
				}
				if(UString.esNuloVacio(personamast.getSexo())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_SEXO));
				}
				
				if (!lst.isEmpty())
					return lst;
				
			}
			
			else if(personamast.getTipopersona().equals("B")) {
				if(UString.esNuloVacio(personamast.getDocumento())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO));
				}
				if(personamast.getTipodocumento().equals("R")) {
					
					if(!personamast.getFlagRuc()) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC_VALID));
					}
					
					if(UString.esNuloVacio(personamast.getDocumentofiscal())) {
						
					}else {
													
						if(personamast.getDocumentofiscal().trim().length() != 11 ) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_RUC));
						}
					}

					
				}
				else if(personamast.getTipodocumento().equals("D")) {
					if(UString.esNuloVacio(personamast.getDocumento())) {
						
					}else {
													
						if(personamast.getDocumento().trim().length() != 8 ) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DNI));
						}
					}
					
					if(UString.esNuloVacio(personamast.getDocumentoidentidad())) {
						lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_DOCUMENTO_IDENTIDAD));
					}else {
													
						if(personamast.getDocumentoidentidad().trim().length() != 8 ) {
							lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_VALID_DOCUMENTO_IDENTIDAD));
						}
					}
				}
				
				if(UString.esNuloVacio(personamast.getApellidopaterno())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_APE_PATERNO));
				}
				if(UString.esNuloVacio(personamast.getApellidomaterno())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_APE_MATERNO));
				}
				if(UString.esNuloVacio(personamast.getNombres())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_NOMBRES));
				}
				if(UString.esNuloVacio(personamast.getSexo())) {
					lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PER_RESTRICCION_SEXO));
				}
				
			}
			

			
			if (!lst.isEmpty())
				return lst;
		}
		
	
		
		
		// TODO Personamast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast) {
		if (personamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		personamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		personamast = prepararInsertar(usuarioActual, personamast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, personamast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Personamast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanPersonamast personamast) {
		if (personamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		personamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		personamast = prepararActualizar(usuarioActual, personamast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, personamast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Personamast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanPersonamast personamast) {
		if (personamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		personamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		personamast = prepararEliminar(usuarioActual, personamast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Personamast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanPersonamastPk pk) {
		BeanPersonamast bean = personamastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer ppersona) {
		return coreEliminar(usuarioActual,new BeanPersonamastPk( ppersona));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanPersonamast personamast) {
		if (personamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		personamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		personamast = prepararAnular(usuarioActual, personamast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Personamast.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanPersonamastPk pk) {
		BeanPersonamast bean = personamastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer ppersona) {
		return coreAnular(usuarioActual,new BeanPersonamastPk( ppersona));
	}

	public DtoComunPersonaMast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunPersonaMast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanPersonamast personamast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, personamast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, personamast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, personamast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, personamast);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
		} else {
			dto.setTransaccionEstado(DominioTransaccion.OK);
			dto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		}
		dto.setAuxFlgValidadoBoolean(Boolean.TRUE);
		return dto;
	}

	public BeanPersonamast prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanPersonamast asAutorizacion) {
		asAutorizacion.setUltimousuario(usuarioActual.getUsuario());
		asAutorizacion.setUltimafechamodif(new Date());
		return asAutorizacion;
	}
}
