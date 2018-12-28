package com.mudfish.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

/**
 * Created by JiangWeiGen on 2018/10/28 0028.
 */
public class FileUtil {
    private static final String PSW = "11111";
    private static Key key;
    private static final String DECRYPT_PROJECT_NAME = "hodgepodge";
    private static final String ENCRYPT_PROJECT_NAME = "hodgepodge_encrypt";
    private static final String DECRYPT = "decrypt";
    private static final String ENCRYPT = "encrypt";


    static {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(PSW.getBytes()));
            key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }//生成密匙
    }

    /**
     * 根据参数生成KEY
     */
    public void getKey(String strKey) {

    }

    /**
     * 文件file进行加密并保存目标文件destFile中
     *
     * @param file     要加密的文件 如c:/test/srcFile.txt
     * @param destFile 加密后存放的文件名 如c:/加密后文件.txt
     */
    public void encrypt(String file, String destFile) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        // cipher.init(Cipher.ENCRYPT_MODE, getKey());
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(destFile);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = cis.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
        cis.close();
        is.close();
        out.close();
    }

    /**
     * 文件采用DES算法解密文件
     *
     * @param file 已加密的文件 如c:/加密后文件.txt
     *             * @param destFile
     *             解密后存放的文件名 如c:/ test/解密后文件.txt
     */
    public void decrypt(String file, String dest) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(dest);
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = is.read(buffer)) > 0) {
            System.out.println();
            cos.write(buffer, 0, r);
        }
        cos.close();
        out.close();
        is.close();
    }

    public void securityAll(String srcDir, String pattern) throws Exception {
        File[] srcFiles = new File(srcDir).listFiles();
        if (srcFiles != null & srcFiles.length > 0) {
            for (File file :
                    srcFiles) {
                if (file.isDirectory()) {
                    securityAll(file.getAbsolutePath(), pattern);
                    continue;
                }
                String srcPath = file.getAbsolutePath();
                String desPath = srcPath.replace(DECRYPT_PROJECT_NAME, ENCRYPT_PROJECT_NAME);
                if (DECRYPT.equals(pattern)) {
                    desPath = srcPath.replace(ENCRYPT_PROJECT_NAME, DECRYPT_PROJECT_NAME);
                }
                File parentFile = new File(desPath).getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                System.out.println(desPath);
                if (DECRYPT.equals(pattern)) {
                    decrypt(srcPath, desPath);
                } else {
                    encrypt(srcPath, desPath);
                }
            }
        }


    }

    public static void main(String[] args) throws Exception {
        FileUtil fileUtil = new FileUtil();
//        fileUtil.securityAll("F:\\hodgepodge", ENCRYPT);
        fileUtil.securityAll("F:\\hodgepodge_encrypt", DECRYPT);

//        fileUtil.encrypt("F:\\hodgepodge\\FileUtil.java", "F:\\hodgepodge_encrypt\\FileUtil.java"); //加密
//        fileUtil.decrypt("F:\\hodgepodge_encrypt\\FileUtil.java", "F:\\hodgepodge2\\FileUtil.java"); //解密

    }

}
