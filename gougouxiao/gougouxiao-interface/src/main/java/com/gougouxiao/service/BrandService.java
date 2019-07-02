package com.gougouxiao.service;

import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.Brand;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * BrandService 服务接口
 * @date 2019-06-29 15:38:23
 * @version 1.0
 */
public interface BrandService {

	/** 添加方法 */
	void save(Brand brand);

	/** 修改方法 */
	void update(Brand brand);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Brand findOne(Serializable id);

	/** 查询全部 */
	List<Brand> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(Brand brand, int page, int rows);
	/**查询所有的品牌（id与name）*/
    List<Map<String,Object>> findAllByIdAndName();
}