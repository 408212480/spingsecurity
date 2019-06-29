/**
 * 
 */
package com.qunincey.brower.security.session;


import com.qunincey.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 默认的session失效处理策略
 * 
 * @author zhailiang
 *
 */

public class QunInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public QunInvalidSessionStrategy(SecurityProperties securityProperties) {
		super(securityProperties);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}

}
