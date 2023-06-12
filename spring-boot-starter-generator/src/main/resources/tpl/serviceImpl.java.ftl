package ${package.Service};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeartist.component.core.api.AbstractService;
import com.codeartist.component.core.api.BaseConverter;
import ${package.Entity}.converter.${entity}Converter;
import ${package.Entity}.${entity};
import ${package.Entity}.param.${entity}Param;
import ${package.Entity}.vo.${entity}VO;
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
public class ${table.serviceName} extends AbstractService<${entity}, ${entity}VO, ${entity}Param> {

    private final ${table.mapperName} ${table.mapperName?uncap_first};
    private final ${entity}Converter ${entity?uncap_first}Converter;

    @Override
    protected BaseMapper<${entity}> getMapper() {
        return this.${table.mapperName?uncap_first};
    }

    @Override
    protected BaseConverter<${entity}, ${entity}Param, ${entity}VO> getConverter() {
        return this.${entity?uncap_first}Converter;
    }
}
