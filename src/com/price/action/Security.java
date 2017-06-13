package com.price.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("security")
@Scope("prototype")
public class Security extends ActionSupport implements ModelDriven {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return null;
    }
}
