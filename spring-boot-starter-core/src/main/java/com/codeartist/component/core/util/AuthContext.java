package com.codeartist.component.core.util;

import com.codeartist.component.core.entity.enums.Headers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 当前登录人信息
 *
 * @author 艾江南
 * @date 2022/7/28
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthContext {

    public static Long getUserId() {
        return getUserId(false);
    }

    public static Long getUserId(boolean auth) {
        String userIdStr = WebUtil.getRequest().getHeader(Headers.USER_ID);
        if (userIdStr != null) {
            return Long.valueOf(userIdStr);
        }
        Assert.state(auth, "UserId不能为空");
        return null;
    }
}
