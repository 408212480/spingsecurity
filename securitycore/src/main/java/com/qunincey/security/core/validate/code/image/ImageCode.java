package com.qunincey.security.core.validate.code.image;

import com.qunincey.security.core.validate.code.ValidateCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-20 22:11
 **/
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(String code, LocalDateTime expireTime, BufferedImage image) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(String code, int expireIn,BufferedImage image){
        super(code, expireIn);
        this.image = image;
    }
}
