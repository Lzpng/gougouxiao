package com.gougouxiao.service;

import com.gougouxiao.pojo.FreightTemplate;

import java.io.Serializable;
import java.util.List;
/**
 * FreightTemplateService 服务接口
 * @date 2019-06-29 15:38:23
 * @version 1.0
 */
public interface FreightTemplateService {

	/** 添加方法 */
	void save(FreightTemplate freightTemplate);

	/** 修改方法 */
	void update(FreightTemplate freightTemplate);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	FreightTemplate findOne(Serializable id);

	/** 查询全部 */
	List<FreightTemplate> findAll();

	/** 多条件分页查询 */
	List<FreightTemplate> findByPage(FreightTemplate freightTemplate, int page, int rows);

}