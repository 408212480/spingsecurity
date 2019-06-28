package com.qunincey.security.core.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.qunincey.security.core.properties.LoginType;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-20 14:27
 **/
@Data
public class BrowserProperties {


    public String loginPage = "/login.html";

    private LoginType loginType= LoginType.JSON;

    private String signUpUrl = "/imooc-signUp.html";

}
