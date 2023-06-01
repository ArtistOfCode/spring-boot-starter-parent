package com.codeartist.component.core.api;

import com.codeartist.component.core.entity.PageInfo;
import com.codeartist.component.core.entity.PageParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 抽象控制类
 *
 * @author J.N.AI
 * @date 2023/6/1
 */
public abstract class AbstractController<VO, Param extends PageParam> {

    protected abstract BaseService<VO, Param> getService();

    @GetMapping
    @ApiOperation("查询详情接口")
    public VO get(Long id) {
        return getService().get(id);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询接口")
    public PageInfo<VO> get(Param param) {
        return getService().get(param);
    }

    @PostMapping
    @ApiOperation("新建更新接口")
    public void save(@RequestBody Param param) {
        getService().save(param);
    }

    @DeleteMapping
    @ApiOperation("删除接口")
    public void delete(Long id) {
        getService().delete(id);
    }
}
