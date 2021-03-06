package com.gougouxiao.service;

import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.Seller;

import java.io.Serializable;
import java.util.List;
/**
 * 商家审核 服务接口
 * @date 2019-06-29 15:38:23
 * @version 1.0
 */
public interface SellerService {

	/** 添加方法 */
	void save(Seller seller);

	/** 修改方法 */
	void update(Seller seller);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Seller findOne(Serializable id);

	/** 查询全部 */
	List<Seller> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(Seller seller, int page, int rows);

	/**根据商家id修改状态*/
	void updateStatus(String sellerId, String status);
}