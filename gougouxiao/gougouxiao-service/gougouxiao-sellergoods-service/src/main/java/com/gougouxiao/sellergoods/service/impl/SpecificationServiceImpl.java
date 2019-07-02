package com.gougouxiao.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.mapper.SpecificationMapper;
import com.gougouxiao.mapper.SpecificationOptionMapper;
import com.gougouxiao.pojo.Specification;
import com.gougouxiao.pojo.SpecificationOption;
import com.gougouxiao.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public void save(Specification specification) {
        try {
            //方法1
            //往tb_specification规格表插入数据
            /*specificationMapper.insertSelective(specification);
            List<SpecificationOption> specificationOptions = specification.getSpecificationOptions();
            //往tb_specitfication_option规格选项表中插入数据
            for (SpecificationOption specificationOption : specificationOptions) {
                //将规格表的id当作规格选项表中的外键
                specificationOption.setSpecId(specification.getId());
                specificationOptionMapper.insertSelective(specificationOption);
            }*/
            //方法2：
            specificationMapper.insertSelective(specification);
            System.out.println(specification.getSpecificationOption().size());
            specificationOptionMapper.save(specification);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改规格
     */
    @Override
    public void update(Specification specification) {
        try {
            //1修改规格数据
            specificationMapper.updateByPrimaryKeySelective(specification);

            //2修改规格选项数据
            //2.1先删除，delete from td_specification_option where pec_id = ?
            SpecificationOption so = new SpecificationOption();
            so.setSpecId(specification.getId());
            //根据实体属性作为条件进行删除，查询条件使用等号
            specificationOptionMapper.delete(so);

            //再添加
            specificationOptionMapper.save(specification);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Serializable id) {

    }

    /**删除选中的规格以及规格选项*/
    @Override
    public void deleteAll(Serializable[] ids) {
     /*   for (Serializable id : ids){
            // 1. 删除规格选项
            SpecificationOption so = new SpecificationOption();
            so.setSpecId((Long)id);
            specificationOptionMapper.delete(so);
            // 2. 删除规格
            specificationMapper.deleteByPrimaryKey(id);
        }*/

        //1、先删除规格选项 delete from db_specification_option where id in(?,?,?)
        Example example = new Example(SpecificationOption.class);
        Example.Criteria criteria = example.createCriteria();
        // spec_id IN (?,?)
        criteria.andIn("specId", Arrays.asList(ids));
        specificationOptionMapper.deleteByExample(example);

        //2、删除规格表中的数据
        // DELETE FROM `tb_specification` WHERE id IN (?,?)
        example = new Example(Specification.class);
        criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(ids));
        specificationMapper.deleteByExample(example);
    }

    @Override
    public Specification findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Specification> findAll() {
        return null;
    }

    /**
     * 多条件分页
     */
    @Override
    public PageResult findByPage(Specification specification, int page, int rows) {
        try {
            //开始分页
            PageInfo<Specification> pageInfo = PageHelper.startPage(page, rows).
                    doSelectPageInfo(new ISelect() {
                        @Override
                        public void doSelect() {
                            specificationMapper.findAll(specification);
                        }
                    });
            return new PageResult(pageInfo.getPages(), pageInfo.getList());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 根据规格id查询规格选项
     */
    public List<SpecificationOption> findSpecOption(Long id) {
        try {
            //SELECT * FROM tb_specification_option WHERE spec_id = 27 ORDER BY orders ASC;
            Example example = new Example(SpecificationOption.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("specId", id);
            example.orderBy("orders").asc();
            //条件查询
            return specificationOptionMapper.selectByExample(example);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    /**查询规格数据*/
    public  List<Map<String,Object>> findSpecList(){
        try {
            return specificationMapper.findSpecList();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
