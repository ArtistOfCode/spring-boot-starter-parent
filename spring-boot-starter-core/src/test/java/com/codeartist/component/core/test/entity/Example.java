package com.codeartist.component.core.test.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 测试类
 *
 * @author 艾江南
 * @date 2022/8/18
 */
@Getter
@Setter
@ToString
public class Example {

    private String name;
    private Integer age;
    private Example example;
}
