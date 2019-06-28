package com.qunincey.security.core.social.qq;

import lombok.Data;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-25 15:35
 **/
@Data
public class QQUserInfo {

    private int ret;

    private String msg;

    private String nickname;

    /**
     *
     */
    private String openId;

    private String figureurl;

    private String figureurl_1;

    private String figureurl_2;

    private String figureurl_qq_1;

    private String figureurl_qq_2;

    private String gender;

    private String is_yellow_vip;

    private String vip;

    private String yellow_vip_level;

    private String level;

    private String is_yellow_year_vip;

    private String is_lost;

    private String province;

    private String city;

    private String year;

    private String constellation;

    private String figureurl_qq;

    private String figureurl_type;

}
