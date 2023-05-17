package com.codeartist.component.core.support.uuid;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * @author 艾江南
 * @date 2023/2/24
 */
public class DefaultIdGenerator implements IdGenerator {

    @Override
    public Long get() {
        return IdWorker.getId();
    }
}
