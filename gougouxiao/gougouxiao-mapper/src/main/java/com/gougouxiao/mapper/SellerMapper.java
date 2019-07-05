package com.gougouxiao.mapper;

import com.gougouxiao.pojo.Seller;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * SellerMapper 数据访问接口
 * @date 2019-06-27 09:12:06
 * @version 1.0
 */
public interface SellerMapper extends Mapper<Seller>{

    /**多功能分页查询*/
    List<Seller> findAll(Seller seller);
}