package com.gougouxiao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.TypeTemplate;
import com.gougouxiao.service.TypeTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

    @Reference
    private TypeTemplateService typeTemplateService;

    /**模块多条件查询分页*/
    @GetMapping("/findByPage")
    public PageResult findByPage(TypeTemplate typeTemplate,
                                 Integer page,
                                 @RequestParam(defaultValue = "10")Integer rows){

        return typeTemplateService.findByPage(typeTemplate,page , rows);
    }

    /**模块的添加操作*/
    @PostMapping("/save")
    public boolean save(@RequestBody TypeTemplate typeTemplate){
        try {
            typeTemplateService.save(typeTemplate);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**修改模板数据*/
    @PostMapping("/update")
    public boolean update(@RequestBody TypeTemplate typeTemplate){
        try {
            typeTemplateService.update(typeTemplate);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**删除模板数据*/
    @GetMapping("/delete")
    public boolean delete(Long[] ids){
        try {
            typeTemplateService.deleteAll(ids);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    /**查询类型模板数据*/
    @GetMapping("/findTypeTemplateList")
    public List<Map<String,Object>> findTypeTemplateList(){
        return typeTemplateService.findTypeTemplateList();
    }
}
