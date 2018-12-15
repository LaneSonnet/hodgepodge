package com.mudfish.security;

import java.io.*;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

/**
 * Created by JiangWeiGen on 2018/10/28 0028.
 */
public class SecurityUtil {
    private Key key;
    public static final String DECRYPT_PROJECT_NAME = "hodgepodge";
    public static final String ENCRYPT_PROJECT_NAME = "hodgepodge_encrypt";
    public static final String DECRYPT = "0";
    public static final String ENCRYPT = "1";

    public SecurityUtil(String psw) {
        //生成密匙
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(psw.getBytes()));
            key = _generator.generateKey();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
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
                String srcPath = file.getAbsolutePath();
                String desPath = srcPath.replace(DECRYPT_PROJECT_NAME, ENCRYPT_PROJECT_NAME);
                if (DECRYPT.equals(pattern)) {
                    desPath = srcPath.replace(ENCRYPT_PROJECT_NAME, DECRYPT_PROJECT_NAME);
                }
                if (isExcludeFile(file)) {
                    continue;
                }
                if (file.isDirectory() && "security".equals(file.getName())) {
                    copyFiles(srcPath, desPath);
                    continue;
                }
                if (file.isDirectory()) {
                    securityAll(file.getAbsolutePath(), pattern);
                    continue;
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

    private boolean isExcludeFile(File file) {
        if (file.isDirectory() && (file.getName().startsWith(".") || "target".equals(file.getName()))) {
            System.out.println("排除目录：" + file.getName());
            return true;
        } else {
            if (file.getName().endsWith(".iml")) {
                System.out.println("排除文件：" + file.getName());
                return true;
            }
        }
        return false;
    }

    private void copyFiles(String srcPath, String desPath) throws Exception {
        File desDir = new File(desPath);
        File srcDir = new File(srcPath);
        if (!desDir.exists()) {
            desDir.mkdir();
        }
        for (File srcFile :
                srcDir.listFiles()) {
            if (isExcludeFile(srcFile)) {
                continue;
            }
            if (srcFile.isDirectory()) {
                copyFiles(srcFile.getAbsolutePath(), desDir.getAbsolutePath() + File.separator +  srcFile.getName());
                continue;
            }
            System.out.println("拷贝文件：" + srcFile.getAbsolutePath());
            copyFile(srcFile, new File(desDir.getAbsolutePath() + File.separator +  srcFile.getName()));
        }
    }
    private void copyFile(File src, File des) throws Exception {
        InputStream is = new FileInputStream(src);
        OutputStream out = new FileOutputStream(des);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = is.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
        out.close();
        is.close();
    }

}
