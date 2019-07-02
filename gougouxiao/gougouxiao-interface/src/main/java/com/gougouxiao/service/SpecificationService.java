package com.gougouxiao.service;

import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.Specification;
import com.gougouxiao.pojo.SpecificationOption;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * SpecificationService 服务接口
 * @date 2019-06-29 15:38:23
 * @version 1.0
 */
public interface SpecificationService {

	/** 添加方法 */
	void save(Specification specification);

	/** 修改方法 */
	void update(Specification specification);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Specification findOne(Serializable id);

	/** 查询全部 */
	List<Specification> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(Specification specification, int page, int rows);

	/** 根据规格id查询规格选项 */
	List<SpecificationOption> findSpecOption(Long id);

	/**查询规格数据*/
	List<Map<String,Object>> findSpecList();

}