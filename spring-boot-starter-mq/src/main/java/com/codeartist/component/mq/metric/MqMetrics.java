package com.codeartist.component.mq.metric;

import com.codeartist.component.core.support.metric.Metrics;
import com.codeartist.component.core.support.mq.bean.MqType;

/**
 * MQ监控
 *
 * @author 艾江南
 * @date 2021/7/23
 */
public class MqMetrics {

    private final Metrics metrics;

    public MqMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public void producer(MqType type, String topic) {
        metrics.counter("mq_producer", "type", type.name(), "topic", topic);
    }

    public void consumer(MqType type, String group, String topic, double amount) {
        metrics.summary("mq_consumer", System.currentTimeMillis() - amount,
                "type", type.name(), "group", group, "topic", topic);
    }

    public void consumerError(MqType type, String group, String topic) {
        metrics.counter("mq_consumer_error",
                "type", type.name(), "group", group, "topic", topic);
    }
}
