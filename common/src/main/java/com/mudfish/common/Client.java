package com.mudfish.common;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Client {

	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Document document = documentBuilderFactory.newDocumentBuilder().parse(new File("common/test.xml"));
		String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC/pAGEnCQqE1Q9k9t+YtuffO+8EQPn+WYuTT6XeuetezJmCC2d9857ovwwUyqn8REshprNNg+LvjcxDYA8uiNk9uNZlTwWOKXoy0AAPgWrbj1yZSyImeugSENo/jwClc4K4DCgOaDSRAedcmWqfL6YaKuVt/sMlsQL7VEPSXRs27GIxOE1OCucdJVmFZAV1VepoPymdOvwoxKkfFxmLo1iuaU8fOoX32az6AlzBZp9bJub8dQ2kNuxCQ+JodfQVaBJ1UU+ezwYdgCC7bhseLCQjl7DmZX4hg4uiQkK0i1BQzaGXcehk/r6N4xYrw86t843QSNoV59StoG8RHQoBT1VAgMBAAECggEAGDuF0Z3o2MUu7sZmh9m6SbWDnvVL2CZqLEqqMn9hEke0vMAl/IacPngCiZ3KlAfgkw/GPVKrrpDz66j4YTVsSlUJtIcxXOH24LPquN0e3GmJLZDri65i5BafJ4SWVAFbV3a3z1raIhxmFhukDWXOt/jsUXc9MH9T9OoiSNuR9D2vFyG6dju4BLzVk340RMFMedt2KbLzCzNp/xrpGqJBi3cFf9B/fhDC8CDgGp3T8zyDpc+ono++Z+KGAhbTzHYprV7esedOc529YYG3OwDo6RWJT46qmxQIpQoRcwKYLqYP3f4xBc+SAD8dVUc6y+1624qfuRM/7aTomffiNYHcAQKBgQD4g7M/N8ijO77C815zpoJPG8WPYPBRhePgveqbCfQQdm4km5FL//AEJxHgDh0I4cQySxYJFydVXHTiITTIzMQvbQuMMqIKEBsHdq4n9tsWEUZYgjQvagWJbm7n7EPdhqZCze3dQ3TQEZkKaCkd4iUWtr+ZEcwJ47vddAA9ddBFAQKBgQDFacAvCv3X4QoF9yewIS57ODI7aESKqFS0ds5L9GvJJEDYjTYmXtrPmfipHcw5OYgCb63YFycjrG/p6+AiSA5stNvimkpPYdfGkCcy5Z/0NQD2ELYdoQxX3MdLITYUIZlCjfWNgciNjTzfKKLa6ekI9n9mkx/07FfF1aSfETpUVQKBgQC2eD0MlfEDqcKPchJP6FqVq1aLyCmsMhr30XqJC+3giJSm3HL60plw8x703GgFNX/RTcCIPC/tRU2tG6SeK3uBNxfbgPHpYZn3CPUm8uI6ecSlCDU4+yfFmQhl0dyis1MwifO1GuqSO9mbAo/uGTYST2yu/5pJSKWQHLRwbCjYAQKBgQCvjN3ZN5YKblVCfsntR2SVMKEC0TL6drx0ip3jT2S2RqXfrJt1va4pJMvKX/QnAzEesX9PhS9J+uK+iwISmi8xnhdXSEtCkkiWi8Q/75CxUp0bNDsu1/MpAuy6s7rqZgJq++BbyQi2T93MuM8xiwau8LzUU4r8e1uGUvkEdwZ+zQKBgQClwDnZENsqUf9kjX4YJ9XUtwdRZRBtUs8SURxsBSoK7XtJSJBJRewl7Ks5zChZbi1UVPa2/J8e7nH/jkEtz04vc8TBX7lqZedPWwPSNCAEo7sxww8apfkNWclPkdZmy7w2qltVWTh0wIRfJ6/u03sxkim5e0KMFSapQpwr52fNWA==";
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv6QBhJwkKhNUPZPbfmLbn3zvvBED5/lmLk0+l3rnrXsyZggtnffOe6L8MFMqp/ERLIaazTYPi743MQ2APLojZPbjWZU8Fjil6MtAAD4Fq249cmUsiJnroEhDaP48ApXOCuAwoDmg0kQHnXJlqny+mGirlbf7DJbEC+1RD0l0bNuxiMThNTgrnHSVZhWQFdVXqaD8pnTr8KMSpHxcZi6NYrmlPHzqF99ms+gJcwWafWybm/HUNpDbsQkPiaHX0FWgSdVFPns8GHYAgu24bHiwkI5ew5mV+IYOLokJCtItQUM2hl3HoZP6+jeMWK8POrfON0EjaFefUraBvER0KAU9VQIDAQAB";
		String signContent = XmlSignature
				.generateXMLDigitalSignature1(document, "CSReq", RSAEncryptUtil.getPrivateRSAKey(privateKey));
//        String signContent = SignUtil.sign(document, privateKey, "CSReq");
		System.out.println(
				"======================================================================================================");
		System.out.println(signContent);
		System.out.println(
				"======================================================================================================");
		Element tag = (Element) document.getElementsByTagName("CSReq").item(0);
		System.out.println(tag.getTagName());
//        boolean isPass = XmlSignature.validateXMLDigitalSignature(signContent, publicKey.getBytes());
//        boolean isPass = XmlSignature.validateXMLDigitalSignature(signContent, Base64.decode(publicKey));
		boolean isPass = SignUtil.check(document, publicKey);
		System.out.println(isPass);
//        System.out.println(document.);

	}
}
