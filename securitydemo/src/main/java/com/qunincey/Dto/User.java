package com.qunincey.Dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.qunincey.validator.MyConstrain;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-15 21:31
 **/
@Data
public class User {

    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};

    @JsonView(UserSimpleView.class)
    @MyConstrain
    public String username;

    @JsonView(UserDetailView.class)
    @NotBlank
    public String password;

    @JsonView(UserSimpleView.class)
    public Long id;

    @Past(message = "生日必须是过去的时间")
    public Date birthDay;
}
