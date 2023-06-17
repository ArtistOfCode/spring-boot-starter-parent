package ${package.Service};

import com.codeartist.component.core.api.AbstractService;
import ${package.Entity}.${entity};
import ${package.Entity}.converter.${entity}Converter;
import ${package.Entity}.param.${entity}Param;
import ${package.Entity}.vo.${entity}VO;
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceName} extends AbstractService<${entity}, ${entity}VO, ${entity}Param> {

    private final ${table.mapperName} ${table.mapperName?uncap_first};
    private final ${entity}Converter ${entity?uncap_first}Converter;

    public ${table.serviceName}(${table.mapperName} ${table.mapperName?uncap_first}, ${entity}Converter ${entity?uncap_first}Converter) {
        super(${table.mapperName?uncap_first}, ${entity?uncap_first}Converter);
        this.${table.mapperName?uncap_first} = ${table.mapperName?uncap_first};
        this.${entity?uncap_first}Converter = ${entity?uncap_first}Converter;
    }
}
