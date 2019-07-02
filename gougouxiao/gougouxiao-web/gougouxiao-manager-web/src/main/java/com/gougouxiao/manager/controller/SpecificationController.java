package com.gougouxiao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.Specification;
import com.gougouxiao.pojo.SpecificationOption;
import com.gougouxiao.service.SpecificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**规格管理*/
@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Reference
    private SpecificationService specificationService;

    /**多条件分页查询*/
    @GetMapping("/findByPage")
    public PageResult findByPage(Specification specification, Integer page,
                                 @RequestParam(defaultValue = "10")Integer rows){
        PageResult byPage = specificationService.findByPage(specification, page, rows);
        System.out.println(byPage.getRows());
        System.out.println(byPage.getPages());
        return byPage;
    }

    /** 添加规格 */
    @PostMapping("/save")
    public boolean save(@RequestBody Specification specification){
        try {
//            System.out.println(specification.getSpecificationOption().size());
            specificationService.save(specification);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    /**修改规格以及规格选项*/
    @PostMapping("/update")
    public boolean update(@RequestBody Specification specification){
        try {
            specificationService.update(specification);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    //根据id查询规格选项；回显数据
    @GetMapping("/findSpecOption")
    public List<SpecificationOption> findSpecOption(Long id){
        return specificationService.findSpecOption(id);
    }

    /**删除多个规格ids*/
    @GetMapping("/delete")
    public boolean delete(Long[] ids){
        try {
            specificationService.deleteAll(ids);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**查询规格数据（id与name）*/
    @GetMapping("/findSpecList")
    public List<Map<String,Object>> findSpecByIdandName(){
       return specificationService.findSpecList();
    }
}
