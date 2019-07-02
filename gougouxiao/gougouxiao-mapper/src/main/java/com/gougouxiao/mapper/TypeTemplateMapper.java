package com.gougouxiao.mapper;

import com.gougouxiao.pojo.TypeTemplate;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * TypeTemplateMapper 数据访问接口
 * @date 2019-06-27 09:12:06
 * @version 1.0
 */
public interface TypeTemplateMapper extends Mapper<TypeTemplate>{

    /** 多条件查询类型模板 */
    List<TypeTemplate> findAll(TypeTemplate typeTemplate);

    /**查询模板的id与name*/
    @Select("SELECT id,name FROM tb_type_template order by id asc ")
    List<Map<String,Object>> findByTypeIdAndName();
}