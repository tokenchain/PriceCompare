package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.dto.LoginDTO;
import com.price.model.User;
import com.price.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("login")
@Scope("prototype")
public class Login extends ActionSupport implements ModelDriven {
    @Resource
    private UserService userService;

    private LoginDTO loginDTO;
    /*
    * 登录返回信息
    * 0代表登陆失败
    * 1代表登录成功
    * */
    private byte loginBack;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String login() throws Exception {
System.out.println(loginDTO.getEmail());
        User user = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        if (user == null) {
            loginBack = 0;
            return SUCCESS;
        }
        Map session = ActionContext.getContext().getSession();
        session.put("hasLogin","true");
        session.put("userInfo", user);
        loginBack = 1;
        return SUCCESS;
    }

    public String logout() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("hasLogin");
        session.remove("userInfo");
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        if(loginDTO == null) {
            loginDTO = new LoginDTO();
        }
        return loginDTO;
    }

    public byte getLoginBack() {
        return loginBack;
    }
}
