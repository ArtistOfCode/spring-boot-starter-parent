package com.codeartist.component.core.annotation;

import com.codeartist.component.core.entity.enums.Environments.ProfileConst;
import org.springframework.context.annotation.Profile;

import java.lang.annotation.*;

/**
 * 非生产环境Profile
 *
 * @author 艾江南
 * @date 2021/11/18
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Profile(ProfileConst.NOT_PROD)
public @interface Development {
}
