package com.price.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.dto.RegisterBackDTO;
import com.price.dto.RegisterDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("register")
@Scope("prototype")
public class Register extends ActionSupport implements ModelDriven {
    private RegisterDTO registerDTO;
    private RegisterBackDTO registerBackDTO;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String register() throws Exception {
        System.out.println(registerDTO.getUsername());
        registerBackDTO = new RegisterBackDTO();
        registerBackDTO.setBackId(1);
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
}
