package com.gougouxiao.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.mapper.TypeTemplateMapper;
import com.gougouxiao.pojo.TypeTemplate;
import com.gougouxiao.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    /**
     * 模板保存数据
     */
    @Override
    public void save(TypeTemplate typeTemplate) {
        try {
            typeTemplateMapper.insertSelective(typeTemplate);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 修改模板数据
     */
    @Override
    public void update(TypeTemplate typeTemplate) {
        try {
            typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public void delete(Serializable id) {

    }
    /**删除模块*/
    @Override
    public void deleteAll(Serializable[] ids) {
        try {
            Example example = new Example(TypeTemplate.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("id", Arrays.asList(ids));
            System.out.println(Arrays.asList(ids));
            typeTemplateMapper.deleteByExample(example);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public TypeTemplate findOne(Serializable id) {
        return null;
    }

    @Override
    public List<TypeTemplate> findAll() {
        return null;
    }

    /**
     * 模块多条件查询分页
     */
    @Override
    public PageResult findByPage(TypeTemplate typeTemplate, int page, int rows) {
        PageInfo<TypeTemplate> pageInfo = PageHelper.startPage(page, rows).
                doSelectPageInfo(new ISelect() {
                    @Override
                    public void doSelect() {
                        typeTemplateMapper.findAll(typeTemplate);
                    }
                });
        return new PageResult(pageInfo.getPages(), pageInfo.getList());
    }

    /**查询类型模板的id与name*/
    public List<Map<String,Object>> findTypeTemplateList(){
        try {
            return typeTemplateMapper.findByTypeIdAndName();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
