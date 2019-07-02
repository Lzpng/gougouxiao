package com.gougouxiao.service;

import com.gougouxiao.pojo.ItemCat;

import java.io.Serializable;
import java.util.List;
/**
 * ItemCatService 服务接口
 * @date 2019-06-29 15:38:23
 * @version 1.0
 */
public interface ItemCatService {

	/** 添加方法 */
	void save(ItemCat itemCat);

	/** 修改方法 */
	void update(ItemCat itemCat);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	ItemCat findOne(Serializable id);

	/** 查询全部 */
	List<ItemCat> findAll();

	/** 多条件分页查询 */
	List<ItemCat> findByPage(ItemCat itemCat, int page, int rows);

	/**根据父级id查询商品*/
    List<ItemCat> findByParentId(Long id);
}