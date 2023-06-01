package ${package.Entity}.converter;

import ${package.Entity}.${entity};
import ${package.Entity}.param.${entity}Param;
import ${package.Entity}.vo.${entity}VO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * ${table.comment!} 实体转换
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper(componentModel = "spring")
public interface ${entity}Converter {

    ${entity} toDo(${entity}Param param);

    ${entity}VO toVo(${entity} ${entity?uncap_first});

    List<${entity}VO> toVo(List<${entity}> ${entity?uncap_first}s);
}
