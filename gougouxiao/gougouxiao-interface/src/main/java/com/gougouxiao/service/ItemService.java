package com.gougouxiao.service;

import com.gougouxiao.pojo.Item;

import java.io.Serializable;
import java.util.List;
/**
 * ItemService 服务接口
 * @date 2019-06-29 15:38:23
 * @version 1.0
 */
public interface ItemService {

	/** 添加方法 */
	void save(Item item);

	/** 修改方法 */
	void update(Item item);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Item findOne(Serializable id);

	/** 查询全部 */
	List<Item> findAll();

	/** 多条件分页查询 */
	List<Item> findByPage(Item item, int page, int rows);

}