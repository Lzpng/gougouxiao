package com.gougouxiao.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.pojo.Goods;
import com.gougouxiao.service.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    @PostMapping("/save")
    public boolean save(@RequestBody Goods goods){
        try {
            //获取商家id
            String sellerId = SecurityUtils.getSubject().getPrincipal().toString();
            goods.setSellerId(sellerId);
            goodsService.save(goods);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
