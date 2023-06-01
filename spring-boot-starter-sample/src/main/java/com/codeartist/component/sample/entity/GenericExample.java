package com.codeartist.component.sample.entity;

import lombok.Data;

/**
 * 泛型测试
 *
 * @author 艾江南
 * @date 2023/4/27
 */
@Data
public class GenericExample<T> {

    private String name;
    private T data;
}
