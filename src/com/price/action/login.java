package com.price.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("login")
@Scope("prototype")
public class login extends ActionSupport {
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
