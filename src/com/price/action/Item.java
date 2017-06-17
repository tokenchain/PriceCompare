package com.price.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("item")
@Scope("prototype")
public class Item extends ActionSupport {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

}
