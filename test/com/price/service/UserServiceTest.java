package com.price.service;

import com.price.dto.RegisterDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;

@ContextConfiguration("/beans.xml")
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    private UserService userService;

    @Test
    public void verifyTest() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("1");
        registerDTO.setPassword("2");
        registerDTO.setPasswordRepeat("2");
        registerDTO.setEmail("3");
        registerDTO.setCaptcha("n45J");
        //System.out.println(userService.verify(registerDTO, "bjQ1ag"));
    }
}
