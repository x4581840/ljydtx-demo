package com.demo.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @Description
 * @Author longjianyong
 * @Date 2019/9/24 10:49 AM
 * @Version 1.0
 **/
@Data
public class UserCond {
    @NotNull(message = "id不能为空")
    private Integer id;
    @NotNull(message = "名字不能为空！")
    private String name;

}
