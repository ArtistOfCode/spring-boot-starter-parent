package ${package.Controller};

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
public class ${table.controllerName} {

    private final ${table.serviceName} ${table.serviceName?uncap_first};
}
