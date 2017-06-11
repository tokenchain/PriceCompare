package com.price.util;


import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Captcha {
    private static Captcha captcha = new Captcha();
    private Random random;
    private File imgFile;
    private String[] fileList;
    //图片URI
    private String IMG_URI = "G:\\IdeaProjects\\PriceCompare\\out\\artifacts\\PriceCompare_war_exploded\\img\\verifyImg";

    private Captcha(){
        random = new Random();
        try{
            imgFile = new File(IMG_URI);
            fileList = imgFile.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Captcha getCaptcha() {
        return captcha;
    }

    public synchronized String getNextCaptcha() {
        if(fileList != null) {
            return fileList[random.nextInt(fileList.length)];
        }
        return "";
    }

    public static void main(String[] args) {
        Captcha captcha = Captcha.getCaptcha();
        BASE64Decoder dec=new BASE64Decoder();
        for (int i = 0; i < 1000; i++) {
            String name = captcha.getNextCaptcha().substring(0,6);
            System.out.println("解密前：" + name);
            byte[] after=name.getBytes();
            try {
                int count = (int)Math.ceil((name.length())/4.0);
                String s = "";
                for (int j = 0; j < (count*4 - name.length()); j++) {
                    s += "=";
                }
                after =dec.decodeBuffer(name+s);//使用BASE64解码
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("解密后:" + new String(after));
        }

    }
}
