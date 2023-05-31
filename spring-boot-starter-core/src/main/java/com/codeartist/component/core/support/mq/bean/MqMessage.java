package com.codeartist.component.core.support.mq.bean;

import com.codeartist.component.core.support.mq.bean.MqHeaders;
import com.codeartist.component.core.support.mq.bean.MqType;
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
public class MqMessage {

    private MqType type;
    private MqHeaders headers;
    private String topic;
    private String tag;
    private Object body;
}
