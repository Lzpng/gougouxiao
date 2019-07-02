package com.gougouxiao.service;

import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.TypeTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TypeTemplateService 服务接口
 * @date 2019-06-29 15:38:23
 * @version 1.0
 */
public interface TypeTemplateService {

	/** 添加方法 */
	void save(TypeTemplate typeTemplate);

	/** 修改方法 */
	void update(TypeTemplate typeTemplate);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	TypeTemplate findOne(Serializable id);

	/** 查询全部 */
	List<TypeTemplate> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(TypeTemplate typeTemplate, int page, int rows);

	/**查询类型模板的id与name*/
    List<Map<String,Object>> findTypeTemplateList();

}