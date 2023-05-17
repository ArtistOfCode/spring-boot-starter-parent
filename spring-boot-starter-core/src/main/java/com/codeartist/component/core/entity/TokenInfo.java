package com.codeartist.component.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户登录信息
 *
 * @author 艾江南
 * @date 2021/10/14
 */
@Getter
@Setter
public class TokenInfo {

    private Long id;

    private String name;

    private String username;

    /**
     * Token过期时间（分钟）
     */
    private Integer expire;

    /**
     * 拥有权限的接口，格式：<code>Method:Path</code>
     * <p>
     * 示例：<code>GET:/api/**</code>
     */
    private List<String> paths;
}
