package com.gougouxiao.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gougouxiao.mapper.ItemCatMapper;
import com.gougouxiao.pojo.ItemCat;
import com.gougouxiao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    /**保存商品数据*/
    @Override
    public void save(ItemCat itemCat) {
        try {
            itemCatMapper.insert(itemCat);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**修改商品数据*/
    @Override
    public void update(ItemCat itemCat) {
        try {
            itemCatMapper.updateByPrimaryKeySelective(itemCat);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Serializable id) {

    }

    /**删除商品数据*/
    @Override
    public void deleteAll(Serializable[] ids) {
        try {
            //定义集合用来存储id
            List<Long> idsList = new ArrayList<>();
            //遍历ids，封装删除的商品id
            for (Serializable id : ids){
                //添加id
                idsList.add((long)id);
                //调用递归的方法
                findByNodeId(id,idsList);
            }
            System.out.println("idsList = " + idsList);
            //通过id来删除商品数据
            Example example = new Example(ItemCat.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("id", idsList);
            itemCatMapper.deleteByExample(example);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    //通过父id来查找子节点的id(递归查询)
    private void findByNodeId(Serializable parentId, List<Long> idsList) {
        //查询下级分类
        List<ItemCat> itemCatsSon = findByParentId((long) parentId);
        //递归退出的条件
        if (itemCatsSon != null && itemCatsSon.size() > 0){
            for (ItemCat itemCat : itemCatsSon){
                //封装字节的id
                idsList.add(itemCat.getId());
                //递归查询
                findByNodeId(itemCat.getId(),idsList);
            }
        }
    }

    @Override
    public ItemCat findOne(Serializable id) {
        return null;
    }

    @Override
    public List<ItemCat> findAll() {
        return null;
    }

    @Override
    public List<ItemCat> findByPage(ItemCat itemCat, int page, int rows) {
        return null;
    }

    /**
     * 根据父级id查询商品
     */
    public List<ItemCat> findByParentId(Long parentId) {
        try {
            //SELECT * FROM `tb_item_cat` WHERE parent_id = ?;
            ItemCat itemCat = new ItemCat();
            itemCat.setParentId(parentId);
            //根据父级id查询商品
            return itemCatMapper.select(itemCat);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
