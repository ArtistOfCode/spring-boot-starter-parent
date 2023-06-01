package ${package.Service};

import ${package.Entity}.converter.${entity}Converter;
import ${package.Mapper}.${table.mapperName};
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@RequiredArgsConstructor
public class ${table.serviceName} {

    private final ${table.mapperName} ${table.mapperName?uncap_first};
    private final ${entity}Converter ${entity?uncap_first}Converter;
}
