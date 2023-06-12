package com.codeartist.component.core.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局常量
 *
 * @author 艾江南
 * @date 2020/10/10
 */
public interface GlobalConstants {

    String SESSION_ID_NAME = "JSESSIONID";

    /**
     * 正则表达式
     */
    interface RegexExpression {

        /**
         * 手机号码正则表达式
         */
        String PHONE_REGEX = "^1[345789]\\d{9}$";
        /**
         * 邮箱正则表达式
         */
        String EMAIL_REGEX = "^\\w+((-\\w+)|(\\.\\w+))*@[A-Za-z0-9]+(([.\\-])[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        /**
         * 用户名正则表达式
         */
        String USERNAME_REGEX = "^[a-zA-Z]+\\w*$";
        /**
         * 密码正则表达式
         */
        String PASSWORD_REGEX = "^[\\w\\W]{6,16}$";
        /**
         * UUID正则表达式
         */
        String UUID_REGEX = "^[0-9a-f]{32}\\.\\w+";
        /**
         * 图片文件名称正则表达式
         */
        String IMAGE_REGEX = "^.+\\.(jpg|jpeg|png|bmp|svg)$";
        /**
         * PDF文件名称正则表达式
         */
        String PDF_REGEX = "^.+\\.(pdf)$";
    }

    interface RedisKey {

        String SESSION_TOKEN = "SESSION_TOKEN:";
        String APP_SESSION_TOKEN = "APP_SESSION_TOKEN:";
        String CAPTCHA_KEY = "CAPTCHA:";
    }

    /**
     * 删除状态
     */
    @Getter
    @AllArgsConstructor
    enum DeleteStatus {

        UNDELETED(0),
        DELETED(1);

        private final Integer status;
    }

    enum EntityEventType {
        SAVE, UPDATE, DELETE
    }
}
