package com.gougouxiao.mapper;

import com.gougouxiao.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * BrandMapper 数据访问接口
 * @date 2019-06-27 09:12:06
 * @version 1.0
 */
public interface BrandMapper extends Mapper<Brand>{

    /** 多条件查询品牌 */
    List<Brand> findAll(Brand brand);

    /** 查询全部品牌(id与name) */
    @Select("select id, name as text from tb_brand")
    List<Map<String,Object>> findAllByIdAndName();
}