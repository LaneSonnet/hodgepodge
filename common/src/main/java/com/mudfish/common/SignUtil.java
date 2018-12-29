package com.mudfish.common;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.xml.security.Init;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.XMLUtils;
import org.bouncycastle.util.encoders.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SignUtil {

	static {
		Init.init();
	}

	public static String sign(Document xmlDocument, String privateKeyStr, String messageEle)
			throws XMLSecurityException, NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		// xmlDocBytes待签名内容，整个报文
//        Document xmlDocument = getXmlDocument(xmlDocBytes);
		XMLSignature xmlSignature = new XMLSignature(xmlDocument, xmlDocument.getDocumentURI(),
				XMLSignature.ALGO_ID_SIGNATURE_RSA);
		// TODO xmlSignature.getElement()插入Message标签下
		Node messageNode = xmlDocument.getElementsByTagName("Message").item(0);
		messageNode.appendChild(xmlSignature.getElement());
		Transforms transforms = new Transforms(xmlDocument);
		transforms.addTransform("http://www.w3.org/2000/09/xmldsig#enveloped-signature");
		xmlSignature.addDocument("#" + messageEle, transforms, "http://www.w3.org/2000/09/xmldsig#sha1");
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyStr));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		xmlSignature.sign(privateKey);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLUtils.outputDOM(xmlDocument, os);
		return os.toString("UTF-8");
	}

	public static boolean check(Document document, String publicKeyStr)
			throws NoSuchAlgorithmException, XMLSecurityException, InvalidKeySpecException {
		XMLSignature signature = new XMLSignature((Element) document.getElementsByTagName("ds:Signature").item(0),
				document.getDocumentURI());
		//signElement是解析的Signature节点
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyStr));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return signature.checkSignatureValue(publicKey);

	}
}
