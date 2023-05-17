package com.codeartist.component.core.entity.enums;

import com.codeartist.component.core.util.SpringContext;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 环境配置
 *
 * @author 艾江南
 * @date 2022/7/15
 */
@Getter
@AllArgsConstructor
public enum Environments {

    LOCAL(ProfileConst.LOCAL, ProfileGroup.LOCAL, "本地环境"),
    TEST(ProfileConst.TEST, ProfileGroup.TEST, "测试环境"),
    PROD(ProfileConst.PROD, ProfileGroup.PROD, "生产环境"),
    ;

    private final String profile;
    private final String group;
    private final String name;

    public boolean not() {
        return !SpringContext.acceptsProfiles(this.getProfile());
    }

    public boolean is() {
        return SpringContext.acceptsProfiles(this.getProfile());
    }

    public interface ProfileGroup {

        String LOCAL = "local";
        String TEST = "test";
        String PROD = "prod";
    }

    public interface ProfileConst {

        String LOCAL = "local";
        String TEST = "test";
        String PROD = "prod";
        String NOT_PROD = "!prod";
    }
}
