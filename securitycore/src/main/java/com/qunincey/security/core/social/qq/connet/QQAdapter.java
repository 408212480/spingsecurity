package com.qunincey.security.core.social.qq.connet;

import com.qunincey.security.core.social.qq.QQ;
import com.qunincey.security.core.social.qq.QQUserInfo;
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

    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {

            QQUserInfo userInfo = qq.getUserInfo();
            connectionValues.setDisplayName(userInfo.getNickname());
            connectionValues.setImageUrl(userInfo.getFigureurl());
            connectionValues.setProfileUrl(null);
//            connectionValues.setProviderUserId(userInfo.get);

    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
