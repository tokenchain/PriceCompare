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

@Component("security")
@Scope("prototype")
public class Security extends ActionSupport implements ModelDriven {
    @Resource
    private UserService userService;
    private String oldPassword;
    private String newPassword;
    private String newPasswordRepeat;
    /*
    * 修改密码返回码
    * 0代表修改成功
    * 1代表密码错误
    * 2代表新旧密码相同
    * 3代表密码不一致
    * 4代表失败
    * */
    private byte returnCode;

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

    public String changePassword() throws Exception {
System.out.println(oldPassword + "|" + newPassword + "|" + newPasswordRepeat);
        Map session = ActionContext.getContext().getSession();
        User user = (User)session.get("userInfo");
        if(!user.getPassword().equals(oldPassword)) {
            returnCode = 1;
            return SUCCESS;
        }
        if(newPassword.equals(oldPassword)) {
            returnCode = 2;
            return SUCCESS;
        }
        if (!newPassword.equals(newPasswordRepeat)) {
            returnCode = 3;
            return SUCCESS;
        }
        if(userService.changePassword(user.getId(), newPassword)) {
            returnCode = 0;
            //更新session信息
            session.remove("hasLogin");
            session.remove("userInfo");
        } else {
            returnCode = 4;
        }
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return null;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }

    public byte getReturnCode() {
        return returnCode;
    }
}
