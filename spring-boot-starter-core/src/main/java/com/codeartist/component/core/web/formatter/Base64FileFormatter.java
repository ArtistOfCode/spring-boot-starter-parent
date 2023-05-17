package com.codeartist.component.core.web.formatter;

import com.codeartist.component.core.entity.Base64File;
import org.springframework.format.Formatter;

import java.util.Locale;

/**
 * Base64文件参数格式化
 *
 * @author 艾江南
 * @date 2021/7/27
 */
public class Base64FileFormatter implements Formatter<Base64File> {

    @Override
    public Base64File parse(String text, Locale locale) {
        return new Base64File(text);
    }

    @Override
    public String print(Base64File object, Locale locale) {
        return new String(object.getBytes());
    }
}
