package net.royal.spring.framework.core;

import java.io.IOException;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class UFileTestCarpetaCopiar {

	public static void main(String[] args) throws IOException {

		String rutaOrigen="D:\\TMP\\c1";
		String rutaDestino="D:\\TMP\\c2";
		DominioTransaccion res = UFile.carpetaCopiar(rutaOrigen, rutaDestino);
		System.out.println(res.getTransaccionEstado());
		System.out.println(res.getTransaccionMensajesCadena());
	}

}
