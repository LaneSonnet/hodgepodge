package com.mudfish.common;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class RSAEncryptUtil {

	// ������ע�ᵽ�������,��ͨ������ʹ��;Ҳ���Բ�ע��,ֱ�Ӵ����ʹ��
	private static final Provider provider = new BouncyCastleProvider();

	private static final String charSet = "utf-8";

	private static String publicKeyStr = null;
	private static String privateKeyStr = null;
	private static PrivateKey privateKey = null;
	private static PublicKey publicKey = null;
	// ����,�ı��,���ɵ���Կ�Իᷢ���仯
	private static final String seedKey = "otsetrading123!@#OTSETRADING";

	// private static Logger logger = LoggerFactory.getLogger(RSAEncryptUtil.class);

	static {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", provider);

			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(seedKey.getBytes(charSet));
			kpg.initialize(2048, secureRandom);
			KeyPair kp = kpg.generateKeyPair();

			privateKey = kp.getPrivate();
			privateKeyStr = new String(Base64.encode(privateKey.getEncoded()));
			publicKey = kp.getPublic();
			publicKeyStr = new String(Base64.encode(publicKey.getEncoded()));
			// logger.debug("======= === privateKey��" + privateKey);
			// logger.debug("======= === privateKeyStr��" + privateKeyStr);
			// logger.debug("======= === publicKey��" + publicKey);
//			 logger.debug("======= === publicKeyStr��" + publicKeyStr);
			System.out.println("======= === privateKey��" + privateKey);
			System.out.println("======= === privateKeyStr��" + privateKeyStr);
			System.out.println("======= === publicKey��" + publicKey);
			System.out.println("======= === publicKeyStr��" + publicKeyStr);
		} catch (Exception e) {
			// logger.error("RSA���ܳ�ʼ��{}", e);
		}
	}

	public static PublicKey getPublicRSAKey(String key) throws Exception {
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(Base64.decode(key));
		KeyFactory kf = KeyFactory.getInstance("RSA", provider);
		return kf.generatePublic(x509);
	}

	public static PrivateKey getPrivateRSAKey(String key) throws Exception {
		PKCS8EncodedKeySpec pkgs8 = new PKCS8EncodedKeySpec(Base64.decode(key));
		KeyFactory kf = KeyFactory.getInstance("RSA", provider);
		return kf.generatePrivate(pkgs8);
	}

	public byte[] encrypt(String input) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA", provider);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] re = cipher.doFinal(input.getBytes(charSet));
		return re;
	}

	public byte[] decrypt(byte[] encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA", provider);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] re = cipher.doFinal(encrypted);
		return re;
	}

	public String decodeJsValue(String jsValue) throws Exception {
		if (jsValue == null) {
			return null;
		}
		// rsa����֮��������11
		if (jsValue.length() <= 11) {
			return jsValue;
		}

		// logger.debug("=======RSA����֮ǰ���ַ��� === jsValue��" + jsValue);
		byte[] input = Hex.decode(jsValue);
		byte[] raw = decrypt(input);

		// ��־λΪ0֮������������Ч�ֽ�
		int i = raw.length - 1;
		while (i > 0 && raw[i] != 0) {
			i--;
		}
		i++;
		byte[] data = new byte[raw.length - i];
		for (int j = i; j < raw.length; j++) {
			data[j - i] = raw[j];
		}
		String decodeJsValue = new String(data, charSet);
		return decodeJsValue;
	}

}
