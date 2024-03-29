package com.mudfish.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.apache.xml.security.Init;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xml.security.utils.Constants;
import org.apache.xml.security.utils.XMLUtils;
import org.apache.xpath.XPathAPI;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlSignature {

	static {
		Init.init();
	}

	/**
	 * 加签
	 */
	public static String generateXMLDigitalSignature1(org.w3c.dom.Document document, String messageType,
			PrivateKey privateKey) throws Exception {
		ByteArrayOutputStream os = null;
		// 如果document直接是W3C的无须转换
//        org.w3c.dom.Document doc = parse(document);// 标准转换DOM4J >>> W3C
		XMLSignature sig = new XMLSignature(document, document.getDocumentURI(),
				XMLSignature.ALGO_ID_SIGNATURE_RSA_SHA1);// 设置各个节点参数
		sig.getSignedInfo().addResourceResolver(new OfflineResolver());
		Node messageNode = document.getElementsByTagName("Message").item(0);
		messageNode.appendChild(sig.getElement());
		Transforms transforms = new Transforms(document);
		transforms.addTransform(Transforms.TRANSFORM_ENVELOPED_SIGNATURE);
		//  针对哪个节点加签
		sig.addDocument("#" + messageType, transforms, Constants.ALGO_ID_DIGEST_SHA1);
		sig.sign(privateKey);
		os = new ByteArrayOutputStream();// 将签名好的XML文档写出
		XMLUtils.outputDOM((Node) document, os);
		return os.toString("UTF-8");
	}

	/**
	 * 验签
	 */
	public static boolean validateXMLDigitalSignature(org.w3c.dom.Document doc, String publickey) throws Exception {
		ByteArrayInputStream is = null;
		try {
/*            CertificateFactory certificatefactory;
            certificatefactory = CertificateFactory.getInstance("X.509");
            // 将二进制转换为文件
            is = new ByteArrayInputStream(publicKeyByte);
            X509Certificate cert = (X509Certificate) certificatefactory.generateCertificate(is);
            PublicKey pk = cert.getPublicKey();*/
//            org.w3c.dom.Document doc = (org.w3c.dom.Document) parse(DocumentHelper.parseText(signatureXml));
			PublicKey pk = RSAEncryptUtil.getPublicRSAKey(publickey);
			Element item = (Element) doc.getElementsByTagName("ds:Signature").item(0);
			Element nscontext = XMLUtils.createDSctx(doc, "ds", "http://www.w3.org/2000/09/xmldsig#");
			Element signElement = (Element) XPathAPI.selectSingleNode(doc, "//ds:Signature[1]", nscontext);
			// 判断是否加密
	          /*if (signElement == null) {
                return false;
            }*/
			XMLSignature signature;
			signature = new XMLSignature(item, doc.getDocumentURI());
			return signature.checkSignatureValue(pk);
		} catch (TransformerException e) {
			throw new Exception("变化异常！Exception is : " + e);
		} catch (XMLSignatureException e) {
			throw new Exception("XML验签异常！Exception is : " + e);
		} catch (XMLSecurityException e) {
			throw new Exception("XML证书异常！Exception is :" + e);
		} catch (CertificateException e) {
			throw new Exception("证书异常！Exception is :" + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new Exception("XML证书异常！Exception is : " + e);
				}
			}
		}
	}

	public static boolean validateXMLDigitalSignature(String signatureXml, byte[] publicKeyByte) throws Exception {
		ByteArrayInputStream is = null;
		try {
			CertificateFactory certificatefactory;
			certificatefactory = CertificateFactory.getInstance("X.509");
			// 将二进制转换为文件
			is = new ByteArrayInputStream(publicKeyByte);
			X509Certificate cert = (X509Certificate) certificatefactory.generateCertificate(is);
			PublicKey pk = cert.getPublicKey();
			org.w3c.dom.Document doc = (org.w3c.dom.Document) parse(DocumentHelper.parseText(signatureXml));
			Element nscontext = XMLUtils.createDSctx(doc, "ds", "http://www.w3.org/2000/09/xmldsig#");
			Element signElement = (Element) XPathAPI.selectSingleNode(doc, "//ds:Signature[1]", nscontext);
			// 判断是否加密
			if (signElement == null) {
				return false;
			}
			XMLSignature signature;
			signature = new XMLSignature(signElement, doc.getDocumentURI());
			return signature.checkSignatureValue(pk);
		} catch (TransformerException e) {
			throw new Exception("变化异常！Exception is : " + e);
		} catch (XMLSignatureException e) {
			throw new Exception("XML验签异常！Exception is : " + e);
		} catch (XMLSecurityException e) {
			throw new Exception("XML证书异常！Exception is :" + e);
		} catch (CertificateException e) {
			throw new Exception("证书异常！Exception is :" + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new Exception("XML证书异常！Exception is : " + e);
				}
			}
		}
	}

	public static PrivateKey getPrivateKey(byte[] privateKeyStream, String privateKeyPassword, String keyAlias)
			throws Exception {
		if (privateKeyStream == null) {
			throw new RuntimeException("nullPointException: privateKey byte is null");
		}
		if (privateKeyPassword == null) {
			throw new RuntimeException("nullPointException: privateKeyPassword is null");
		}
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		InputStream inputStream = new ByteArrayInputStream(privateKeyStream);
		keyStore.load(inputStream, privateKeyPassword.toCharArray());
		PrivateKey p = (PrivateKey) keyStore.getKey(keyAlias, privateKeyPassword.toCharArray());
		return p;
	}

	public static PublicKey getPublicKey(byte[] publicKeyStream) throws Exception {
		if (publicKeyStream == null) {
			throw new RuntimeException("nullPointException: publicKey byte is null");
		}
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		InputStream inputStream = new ByteArrayInputStream(publicKeyStream);
		Certificate c = cf.generateCertificate(inputStream);
		return c.getPublicKey();
	}

	/**
	 * 实现dom4j向org.w3c.dom.Document的转换
	 */
	public static org.w3c.dom.Document parse(Document doc) throws Exception {
		if (doc == null) {
			return (null);
		}
		InputStream is = null;
		try {
			is = IOUtils.toInputStream(doc.asXML(), "UTF-8");
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(true);
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			is.close();
			return docBuilder.parse(is);
		} catch (IOException e) {
			throw new Exception("XML证书异常！Exception is : " + e);
		} catch (SAXException e) {
			throw new Exception("证书转换异常！Exception is : " + e);
		} catch (ParserConfigurationException e) {
			throw new Exception("证书转换异常！Exception is : " + e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new Exception("XML证书异常！Exception is : " + e);
				}
			}
		}
	}
}
