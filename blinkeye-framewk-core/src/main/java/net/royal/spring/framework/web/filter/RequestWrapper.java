package net.royal.spring.framework.web.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.web.constante.ConstanteFiltro;

@SuppressWarnings("unchecked")
public class RequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;

	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		this.body = StreamUtils.copyToByteArray(request.getInputStream());

		if (this.body.length > 0) {

			Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);
			String dataEncriptada = (String) jsonRequest.get("codigo");

			if (dataEncriptada != null) {
				String dataDecrypted = "";
				// AES Encryption
				dataDecrypted = Aes2.aesDecrypt(dataEncriptada, ConstanteFiltro.AES_KEY);
				dataDecrypted = new String(Base64.getDecoder().decode(dataDecrypted.getBytes()), StandardCharsets.UTF_8);
				this.body = dataDecrypted.getBytes();
			}
		}

	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new ServletInputStreamWrapper(this.body);
	}
}

class ServletInputStreamWrapper extends ServletInputStream {
	private InputStream inputStream;

	public ServletInputStreamWrapper(byte[] body) {
		this.inputStream = new ByteArrayInputStream(body);
	}

	@Override
	public boolean isFinished() {
		try {
			return inputStream.available() == 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener listener) {
	}

	@Override
	public int read() throws IOException {
		return this.inputStream.read();
	}
}

class Aes2 {


	// Algoritmo
	private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

	public static String aesDecrypt(String encrypt) {
		try {
			return aesDecrypt(encrypt, ConstanteFiltro.AES_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String aesEncrypt(String content) {
		try {
			return aesEncrypt(content, ConstanteFiltro.AES_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String binary(byte[] bytes, int radix) {
		return new BigInteger(1, bytes).toString(radix); // donde 1 representa un n\u00famero positivo
	}

	public static String base64Encode(byte[] bytes) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
	}

	public static byte[] base64Decode(String base64Code) throws Exception {
		return StringUtils.isEmpty(base64Code) ? null : Base64.getDecoder().decode(base64Code.getBytes());
	}

	public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

		return cipher.doFinal(content.getBytes("utf-8"));
	}

	public static String aesEncrypt(String content, String encryptKey) throws Exception {
		return base64Encode(aesEncryptToBytes(content, encryptKey));
	}

	public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);

		Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		return new String(decryptBytes);
	}

	public static String aesDecrypt(String encryptStr, String decryptKey) {
		try {

			if (StringUtils.isEmpty(encryptStr)) {
				return null;
			} else {

				// String p2 = aesDecryptByBytes(encryptStr.getBytes(), decryptKey);

				// byte[] decodedBytes = Base64.getDecoder().decode(p2);
				// String decodedString = new String(decodedBytes);

				return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}