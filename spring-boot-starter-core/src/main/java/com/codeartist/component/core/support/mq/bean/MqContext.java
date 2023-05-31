package com.codeartist.component.core.support.mq.bean;

import lombok.*;

import java.lang.reflect.Type;

/**
 * MQ消息实体
 *
 * @author 艾江南
 * @date 2021/5/8
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MqContext<T> {

    private MqType type;
    private MqHeaders headers;
    private String group;
    private String topic;
    private String tag;
    private T body;
    private Type bodyType;
}
