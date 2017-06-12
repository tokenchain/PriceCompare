package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("homepage")
@Scope("prototype")
public class Homepage extends ActionSupport implements ModelDriven {


    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        User user = (User)session.get("userInfo");
        System.out.println(user.getId());
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return null;
    }

}
