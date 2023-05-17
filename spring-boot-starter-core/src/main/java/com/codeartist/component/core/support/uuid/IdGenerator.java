package com.codeartist.component.core.support.uuid;

import java.util.function.Supplier;

/**
 * ID生成器
 *
 * @author 艾江南
 * @date 2023/2/21
 */
@FunctionalInterface
public interface IdGenerator extends Supplier<Long> {

}
