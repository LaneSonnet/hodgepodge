package com.mudfish.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by JiangWeiGen on 2018/10/28 0028.
 */
public class Client {
    public static void main(String[] args) throws Exception {
        ;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入密码：");
        String psw = reader.readLine();
        System.out.println("请选择您当前操作为加密（1）或者解码（0）：");
        String pattern = reader.readLine();
        if (psw == null || pattern == null) {
            System.out.println("输入不能为空");
            return;
        }
        if (!SecurityUtil.ENCRYPT.equals(pattern) && !SecurityUtil.DECRYPT.equals(pattern)) {
            System.out.println("无相关操作【" + pattern + "】");
            return;
        }
        String projectPath = getProjectPath();
        if (SecurityUtil.DECRYPT.equals(pattern)) {
            projectPath = projectPath.replace(SecurityUtil.DECRYPT_PROJECT_NAME, SecurityUtil.ENCRYPT_PROJECT_NAME);
        }
        new SecurityUtil(psw).securityAll(projectPath, pattern);
    }

    private static String getProjectPath() {
        String classPath = Client.class.getResource("/").getPath();
        String projectPath = classPath.substring(1, classPath.lastIndexOf(SecurityUtil.DECRYPT_PROJECT_NAME) + SecurityUtil.DECRYPT_PROJECT_NAME.length());
        System.out.println("自动获取项目路径：【" + projectPath + "】");
        return projectPath;
    }
}
