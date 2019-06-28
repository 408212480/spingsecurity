/**
 * 
 */
package com.qunincey.security.core.properties;

import lombok.Data;

/**
 * 微信登录配置项
 * 
 * @author zhailiang
 *
 */
@Data
public class WeixinProperties {

	/**
	 * Application id.
	 */
	private String appId;

	/**
	 * Application secret.
	 */
	private String appSecret;
	
	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
	 */
	private String providerId = "weixin";


	

}
