package net.royal.spring.framework.core;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class UPdf {
	public static byte[] encriptarArregloByte(byte[] archivopdf, String clave, String rutaCompletaDestino)
			throws IOException, DocumentException {
		PdfReader pdfReader = new PdfReader(archivopdf);
		PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(rutaCompletaDestino));
		pdfStamper.setEncryption(clave.getBytes(), clave.getBytes(), PdfWriter.ALLOW_PRINTING,
				PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
		pdfStamper.close();
		return null;
	}
}
