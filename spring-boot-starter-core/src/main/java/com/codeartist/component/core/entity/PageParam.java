package com.codeartist.component.core.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codeartist.component.core.exception.BadRequestException;
import com.codeartist.component.core.util.Assert;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页参数
 *
 * @author 艾江南
 * @date 2020/9/21
 */
@Getter
@Setter
public class PageParam {

    /**
     * 页码
     */
    private Integer pageNo = 1;
    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    public <T> IPage<T> page() {
        pageNo = pageNo < 1 ? 1 : pageNo;
        Assert.state(pageSize < 1 || pageSize > 200, () -> new BadRequestException("每页记录数在1～200之间"));
        return new Page<>(pageNo, pageSize);
    }
}
