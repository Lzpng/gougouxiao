package com.gougouxiao.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gougouxiao.mapper.GoodsDescMapper;
import com.gougouxiao.mapper.GoodsMapper;
import com.gougouxiao.pojo.Goods;
import com.gougouxiao.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
/**商品服务*/
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDescMapper goodsDescMapper;

    /**新增商品*/
    @Override
    public void save(Goods goods) {
        try {
            // 设置未申核状态
            goods.setAuditStatus("0");
            goodsMapper.insertSelective(goods);
            //为商品描述对象设置主键id
            goods.getGoodsDesc().setGoodsId(goods.getId());
            goodsDescMapper.insertSelective(goods.getGoodsDesc());
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Goods goods) {

    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {

    }

    @Override
    public Goods findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Goods> findAll() {
        return null;
    }

    @Override
    public List<Goods> findByPage(Goods goods, int page, int rows) {
        return null;
    }
}
