package com.gougouxiao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.pojo.ItemCat;
import com.gougouxiao.service.ItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference(timeout = 10000)
    private ItemCatService itemCatService;

    @GetMapping("/findItemCatParentId")
    public List<ItemCat> findItemCatParentId(Long parentId){
//        System.out.println(itemCatService.findByParentId(parentId));
        return itemCatService.findByParentId(parentId);
    }

    /**添加商品数据*/
    @PostMapping("/save")
    public boolean save(@RequestBody ItemCat itemCat){
        try {
            itemCatService.save(itemCat);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**修改商品数据*/
    @PostMapping("/update")
    public boolean update(@RequestBody ItemCat itemCat){
        try {
            itemCatService.update(itemCat);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    /**删除商品数据*/
    @GetMapping("/delete")
    public boolean delete(Long[] ids){
        try {
            itemCatService.deleteAll(ids);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
