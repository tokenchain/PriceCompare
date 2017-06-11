package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.dto.RegisterBackDTO;
import com.price.dto.RegisterDTO;
import com.price.util.Captcha;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Map;

@Component("register")
@Scope("prototype")
public class Register extends ActionSupport implements ModelDriven {
    private Captcha captcha;
    private BASE64Decoder dec;
    private RegisterDTO registerDTO;
    private RegisterBackDTO registerBackDTO;
    private String captchaName;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String register() throws Exception {
        System.out.println(registerDTO.getCaptcha());
        registerBackDTO = new RegisterBackDTO();
        registerBackDTO.setBackId(1);
        return SUCCESS;
    }

    public String getCaptchaImg() throws Exception {
        captcha = Captcha.getCaptcha();
        dec=new BASE64Decoder();
        String name = captcha.getNextCaptcha().substring(0,6);
        /*byte[] codeBytes = null;
        String code = null;
        //加上等于号后，进行解码
        try {
            int count = (int)Math.ceil((name.length())/4.0);
            for (int j = 0; j < (count*4 - name.length()); j++) {
                name += "=";
            }
            codeBytes =dec.decodeBuffer(name);//使用BASE64解码
            code = new String(codeBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(code != null) {
            Map session = ActionContext.getContext().getSession();
            session.put("captcha_register", code);
        }*/
        Map session = ActionContext.getContext().getSession();
        session.put("captcha_register", name);
        captchaName = name;
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        if(registerDTO == null) {
            registerDTO = new RegisterDTO();
        }
        return registerDTO;
    }

    public void setRegisterDTO(RegisterDTO registerDTO) {
        this.registerDTO = registerDTO;
    }

    public RegisterBackDTO getRegisterBackDTO() {
        return registerBackDTO;
    }

    public String getCaptchaName() {
        return captchaName;
    }
}
