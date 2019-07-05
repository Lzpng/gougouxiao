package com.gougouxiao.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.mapper.SellerMapper;
import com.gougouxiao.pojo.Seller;
import com.gougouxiao.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**商家服务*/
@Service
@Transactional
public class SellerServiceImpl implements SellerService{
    @Autowired
    private SellerMapper sellerMapper;

    /**商家申请入驻*/
    @Override
    public void save(Seller seller) {
        try {
            seller.setStatus("0");
            seller.setCreateTime(new Date());
            sellerMapper.insert(seller);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {

    }

    /**根据id查询商家*/
    @Override
    public Seller findOne(Serializable id) {
        return sellerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    /** 多条件分页查询 */
   public PageResult findByPage(Seller seller, int page, int rows){
       try {
           PageInfo<Seller> pageInfo = PageHelper.startPage(page, rows)
                   .doSelectPageInfo(new ISelect() {
               @Override
               public void doSelect() {
                sellerMapper.findAll(seller);
               }
           });
           return new PageResult(pageInfo.getPages(),pageInfo.getList());
       }catch (Exception ex){
           throw new RuntimeException(ex);
       }

    }
    /**根据商家id修改状态*/
    public void updateStatus(String sellerId, String status){
        try {
            Seller seller = new Seller();
            seller.setSellerId(sellerId);
            seller.setStatus(status);
            sellerMapper.updateByPrimaryKeySelective(seller);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
