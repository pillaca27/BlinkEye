package net.royal.spring.framework.core;

import java.io.IOException;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class UFileTestMover {

	public static void main(String[] args) throws IOException {

		String rutaOrigen="D:\\TMP\\β¶NFORME N° 16-2022 EXP. N° 2358-2022 Informe de requerimiento para contratacion exonerad@ de las empres@s terceras de recuperaciones.pdf";
		String rutaDestino="D:\\\\TMP\\\\log4j3.xml";
		DominioTransaccion res = UFile.moverArchivo(rutaOrigen, rutaDestino);
		System.out.println(res.getTransaccionEstado());
		System.out.println(res.getTransaccionMensajesCadena());
	}

}
