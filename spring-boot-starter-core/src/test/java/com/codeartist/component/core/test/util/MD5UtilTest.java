package com.codeartist.component.core.test.util;

import com.codeartist.component.core.test.JUnit5TestRunner;
import com.codeartist.component.core.util.MD5Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * @author 艾江南
 * @date 2022/11/24
 */
class MD5UtilTest extends JUnit5TestRunner {

    @Test
    public void md5() {
        String data = UUID.randomUUID().toString().replaceAll("-", "");
        String md5 = MD5Util.md5(data);
        Assertions.assertNotNull(md5);

        data = UUID.randomUUID().toString().replaceAll("-", "");
        String salt = UUID.randomUUID().toString();
        md5 = MD5Util.md5(data, salt);
        Assertions.assertNotNull(md5);
    }
}