package com.codeartist.component.metric.core;

import com.codeartist.component.core.support.metric.Metrics;

/**
 * 指标绑定接口
 *
 * @author 艾江南
 * @date 2021/7/23
 */
public interface MetricBinder {

    void bindTo(Metrics metrics);
}
