package com.gougouxiao.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gougouxiao.pojo.SpecificationOption;
import com.gougouxiao.service.SpecificationOptionService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class SpecificationOptionImpl implements SpecificationOptionService {

    @Override
    public void save(SpecificationOption specificationOption) {

    }

    @Override
    public void update(SpecificationOption specificationOption) {

    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {

    }

    @Override
    public SpecificationOption findOne(Serializable id) {
        return null;
    }

    @Override
    public List<SpecificationOption> findAll() {
        return null;
    }

    @Override
    public List<SpecificationOption> findByPage(SpecificationOption specificationOption, int page, int rows) {
        return null;
    }
}
