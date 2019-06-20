package com.qunincey.security.core;

import com.qunincey.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-20 14:34
 **/
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {
}
