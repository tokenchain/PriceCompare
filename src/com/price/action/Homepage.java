package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.model.User;
import com.price.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("homepage")
@Scope("prototype")
public class Homepage extends ActionSupport implements ModelDriven {
    @Resource
    private UserService userService;
    public String newUsername;
    /*修改用户名返回码
    * 0修改成功
    * 1用户名与旧用户名相同
    * 2修改失败
    * */
    public int returnCode;

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        User user = (User)session.get("userInfo");
        if(user != null) {
            System.out.println(user.getId());
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public String changeUsername() throws Exception {
//System.out.println("new"+newUsername);
        Map session = ActionContext.getContext().getSession();
        User user = (User)session.get("userInfo");
//System.out.println("old"+user.getUsername());
        if(newUsername.equals(user.getUsername())) {
            //新用户名与旧用户名相同
            returnCode = 1;
            return SUCCESS;
        }
        if(userService.changeUsername(user.getId(), newUsername)) {
            //修改成功
            returnCode = 0;
            //修改session信息
            user.setUsername(newUsername);
            user.setUsername_changed(true);
            session.put("userInfo", user);
        } else {
            //修改失败
            returnCode = 2;
        }

        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return null;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public int getReturnCode() {
        return returnCode;
    }
}
