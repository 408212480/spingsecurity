package com.qunincey.security.core.social.qq.connet;

import com.qunincey.security.core.social.qq.api.QQ;
import com.qunincey.security.core.social.qq.api.QQUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-25 19:16
 **/

public class QQAdapter implements ApiAdapter<QQ> {

    private Logger logger = LoggerFactory.getLogger(QQAdapter.class);

    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {


        try {
            QQUserInfo userInfo = qq.getUserInfo();
            connectionValues.setDisplayName(userInfo.getNickname());
            connectionValues.setImageUrl(userInfo.getFigureurl());
            connectionValues.setProfileUrl(null);
            connectionValues.setProviderUserId(userInfo.getOpenId());
            logger.info("获取到的值"+userInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return UserProfile.EMPTY;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
