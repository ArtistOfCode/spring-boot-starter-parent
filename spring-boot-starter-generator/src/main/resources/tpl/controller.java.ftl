package ${package.Controller};

import com.codeartist.component.core.api.AbstractController;
import com.codeartist.component.core.api.BaseService;
import ${package.Entity}.param.${entity}Param;
import ${package.Entity}.vo.${entity}VO;
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!} 控制器
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = "${table.comment!}")
@Validated
@RestController
@RequestMapping("/api<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@RequiredArgsConstructor
public class ${table.controllerName} extends AbstractController<${entity}VO, ${entity}Param> {

    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @Override
    protected BaseService<${entity}VO, ${entity}Param> getService() {
        return this.${table.serviceName?uncap_first};
    }
}
