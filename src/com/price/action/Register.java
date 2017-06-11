package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.dto.RegisterBackDTO;
import com.price.dto.RegisterDTO;
import com.price.service.UserService;
import com.price.util.Captcha;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Component("register")
@Scope("prototype")
public class Register extends ActionSupport implements ModelDriven {
    @Resource
    private UserService userService;

    private Captcha captcha;
    private RegisterDTO registerDTO;
    private Byte registerBack;
    private String captchaName;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String register() throws Exception {
System.out.println(registerDTO.getCaptcha());
        Map session = ActionContext.getContext().getSession();
        String captchaCode = (String)session.get("captcha_register");
        byte returnCode = userService.save(registerDTO, captchaCode);
        registerBack = returnCode;
        session.remove("captcha_register");
        return SUCCESS;
    }

    public String getCaptchaImg() throws Exception {
        captcha = Captcha.getCaptcha();
        String name = captcha.getNextCaptcha().substring(0,6);
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

    public Byte getRegisterBack() {
        return registerBack;
    }

    public String getCaptchaName() {
        return captchaName;
    }
}
