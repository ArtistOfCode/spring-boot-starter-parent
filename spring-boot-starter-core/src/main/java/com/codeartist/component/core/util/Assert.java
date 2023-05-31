package com.codeartist.component.core.util;


import com.codeartist.component.core.api.ICode;
import com.codeartist.component.core.exception.BusinessException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 业务断言
 *
 * @author 艾江南
 * @date 2020/9/8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Assert {

    /**
     * 判断表达式是否为真
     */
    public static void state(boolean expression, String message) {
        if (expression) {
            error(message);
        }
    }

    /**
     * 判断表达式是否为真
     */
    public static void state(boolean expression, ICode code) {
        if (expression) {
            error(code);
        }
    }

    /**
     * 判断表达式是否为真
     */
    public static void state(boolean expression, Supplier<? extends RuntimeException> exception) {
        if (expression) {
            throw exception.get();
        }
    }

    /**
     * 判断对象是否为空
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            error(message);
        }
    }

    /**
     * 判断对象是否为空
     */
    public static void notNull(Object object, ICode code) {
        if (object == null) {
            error(code);
        }
    }

    /**
     * 判断对象是否为空
     */
    public static void notNull(Object object, Supplier<? extends RuntimeException> exception) {
        if (object == null) {
            throw exception.get();
        }
    }

    /**
     * 判断集合是否为空（为空抛出）
     */
    public static void notEmpty(Collection<?> object, String message) {
        if (object == null || object.isEmpty()) {
            error(message);
        }
    }

    /**
     * 判断集合是否为空（为空抛出）
     */
    public static void notEmpty(Collection<?> object, ICode code) {
        if (object == null || object.isEmpty()) {
            error(code);
        }
    }

    /**
     * 判断集合是否为空（为空抛出）
     */
    public static void notEmpty(Collection<?> object, Supplier<? extends RuntimeException> exception) {
        if (object == null || object.isEmpty()) {
            throw exception.get();
        }
    }

    /**
     * 判断集合是否为空（不为空抛出）
     */
    public static void isEmpty(Collection<?> object, String message) {
        if (object != null && !object.isEmpty()) {
            error(message);
        }
    }

    /**
     * 判断集合是否为空（不为空抛出）
     */
    public static void isEmpty(Collection<?> object, ICode code) {
        if (object != null && !object.isEmpty()) {
            error(code);
        }
    }

    /**
     * 判断集合是否为空（不为空抛出）
     */
    public static void isEmpty(Collection<?> object, Supplier<? extends RuntimeException> exception) {
        if (object != null && !object.isEmpty()) {
            throw exception.get();
        }
    }

    /**
     * 判断字符串是否为空字符串
     */
    public static void notBlank(String string, String message) {
        if (string == null || string.trim().isEmpty()) {
            error(message);
        }
    }

    /**
     * 判断字符串是否为空字符串
     */
    public static void notBlank(String string, ICode code) {
        if (string == null || string.trim().isEmpty()) {
            error(code);
        }
    }

    /**
     * 判断字符串是否为空字符串
     */
    public static void notBlank(String string, Supplier<? extends RuntimeException> exception) {
        if (string == null || string.trim().isEmpty()) {
            throw exception.get();
        }
    }

    private static void error(String message) {
        throw new BusinessException(message);
    }

    private static void error(ICode code) {
        throw new BusinessException(code);
    }
}
