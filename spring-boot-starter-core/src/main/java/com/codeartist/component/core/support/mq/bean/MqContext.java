package com.codeartist.component.core.support.mq.bean;

import lombok.*;

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
public class MqContext {

    private MqType type;
    private MqHeaders headers;
    private String group;
    private String topic;
    private String tag;
    private String record;
}
