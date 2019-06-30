/**
 * 
 */
package com.qunincey.app.security.social;


import com.qunincey.security.core.properties.SecurityConstants;
import com.qunincey.security.core.social.QunSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author zhangyang
 * 在App初始化完成之后修改掉signup的路径
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

	/** (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(Object, String)
	 *
	 * 在bean实例化之前会调用这个方法，这里什么都不做，原样返回
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/** (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(Object, String)
	 * 在imoocSocialSecurityConfig bean实例化之后修改其signUpUrl
	 * 由于所有bean在实例化之后都要只需这个方法，所以要判断一下bean的名字
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(StringUtils.equals(beanName, "qunSocialSecurityConfig")){
			QunSpringSocialConfigurer config = (QunSpringSocialConfigurer)bean;
			config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
			return config;
		}
		return bean;
	}

}
