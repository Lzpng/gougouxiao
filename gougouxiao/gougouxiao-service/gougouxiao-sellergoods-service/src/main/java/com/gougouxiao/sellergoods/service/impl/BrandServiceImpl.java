package com.gougouxiao.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.mapper.BrandMapper;
import com.gougouxiao.pojo.Brand;
import com.gougouxiao.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 品牌接口实现类
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 添加品牌
     */
    @Override
    public void save(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 修改数据
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {
        System.out.println("ids = " + ids);//ids = [Ljava.io.Serializable;@147d35e1
        //delete from tb_brand where id in (?,?,?)
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        System.out.println(Arrays.asList(ids));//[26, 25]
        criteria.andIn("id", Arrays.asList(ids));
        brandMapper.deleteByExample(example);
    }

    @Override
    public Brand findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Brand> findAll() {
        //开始分页，这种方法不易扩展，如果对多个页面进行分页的话。就写不了
       /* PageHelper.startPage(1, 5);
        //因为这里需要连接起来才行。
        List<Brand> brandList = brandMapper.findAll();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);*/

        //分页扩展
//        PageInfo<Brand> pageInfo = PageHelper.startPage(1, 10).
//                doSelectPageInfo(new ISelect() {
//                    @Override
//                    public void doSelect() {
//                        brandMapper.selectAll();
//                    }
//                });
//
//        System.out.println("总记录数" + pageInfo.getTotal());
//        System.out.println("总页数" + pageInfo.getPages());

        return brandMapper.selectAll();
    }

    /**
     * 多条件分页查询
     */
    @Override
    public PageResult findByPage(Brand brand, int page, int rows) {
        // 开始分页
       PageInfo<PageResult> pageInfo =  PageHelper.startPage(page, rows)
                .doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        brandMapper.findAll(brand);
                    }
                });
       //有参构造函数
        return new PageResult(pageInfo.getPages(),pageInfo.getList());
    }

    /**查询所有的品牌（id与name）*/
    public List<Map<String,Object>> findAllByIdAndName(){
        try {
        //  {id: 1, text: '华为'}, {id: 2, text: '小米'}, {id: 3, text: '红米'}
            return brandMapper.findAllByIdAndName();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
