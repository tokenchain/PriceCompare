package com.price.service;

import com.price.dao.UserDAO;
import com.price.dto.RegisterDTO;
import com.price.model.User;
import com.price.util.MailUtil;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Component("userService")
public class UserService {
    @Resource
    private UserDAO userDAO;
    private BASE64Decoder dec;

    /*
    * 保存成功返回0
    * 验证码错误返回1
    * 密码不一致返回2
    * 用户信息有误返回3
    * */
    public byte save(RegisterDTO registerDTO, String captchaCode) {
        byte returnCode = verify(registerDTO, captchaCode);
        if(returnCode == 0) {
            UUID uuid = UUID.randomUUID();
            //System.out.println(uuid);
            User user = new User(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getEmail(),
                    (byte)0, uuid.toString());
            if(save(user)) {
                //发送
                sendActiveMail(registerDTO.getEmail(), uuid.toString());
                return 0;
            } else {
                return 3;
            }
        }
        return returnCode;
    }

    public boolean save(User user) {
        if(verify(user)) {
            try{
                userDAO.save(user);
            } catch (SQLException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* *验证表单信息格式
    *  密码输入不一致返回2
    *  验证码不正确返回1
    *  验证通过返回0
    * */
    private byte verify(RegisterDTO registerDTO, String captchaCodeSrc) {
        if(registerDTO.getPassword() == null || !registerDTO.getPassword().equals(registerDTO.getPasswordRepeat())) {
            return 2;
        }

        String captchaCode = registerDTO.getCaptcha().toLowerCase();
        System.out.println("解码前：" + captchaCode + "|" + captchaCodeSrc);
        dec=new BASE64Decoder();
        if(captchaCode == null || captchaCodeSrc == null) {
            return 1;
        } else {
            //加上等于号后，进行解码
            try {
                int count = (int)Math.ceil((captchaCodeSrc.length())/4.0);
                for (int j = 0; j < (count*4 - captchaCodeSrc.length()); j++) {
                    captchaCodeSrc += "=";
                }
                byte[] codeBytes =dec.decodeBuffer(captchaCodeSrc);//使用BASE64解码
                captchaCodeSrc = new String(codeBytes);
            } catch (IOException e) {
                return 1;
            }
            System.out.println("解码后：" + captchaCode + "|" + captchaCodeSrc);
            if(!captchaCode.equals(captchaCodeSrc)) {
                return 1;
            }
        }
        return 0;
    }

    /* *验证User信息格式
    *  用户名格式
    *  密码格式
    *  email格式
    * */
    private boolean verify(User user) {
        if(!user.getUsername().matches("^[a-zA-Z0-9_\\u4E00-\\u9FA5]{3,15}$")) {
            return false;
        }
        if(!user.getPassword().matches("^[a-zA-Z0-9_]{3,20}$")) {
            return false;
        }
        if(!user.getEmail().matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$")) {
            return false;
        }
        return true;
    }

    /*发送激活邮件
    * */
    public void sendActiveMail(String email, String code) {
        new Thread(new MailUtil(email, code)).start();
    }

    public boolean active(String code) {
        try {
            userDAO.active(code);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
