package com.gougouxiao.mapper;

import com.gougouxiao.pojo.Specification;
import com.gougouxiao.pojo.SpecificationOption;
import tk.mybatis.mapper.common.Mapper;

/**
 * SpecificationOptionMapper 数据访问接口
 * @date 2019-06-27 09:12:06
 * @version 1.0
 */
public interface SpecificationOptionMapper extends Mapper<SpecificationOption>{

    /** 批量插入数据 */
    void save(Specification specification);
}