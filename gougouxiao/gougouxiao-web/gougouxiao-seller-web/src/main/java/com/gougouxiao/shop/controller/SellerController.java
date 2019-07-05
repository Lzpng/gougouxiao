package com.gougouxiao.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.pojo.Seller;
import com.gougouxiao.service.SellerService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**商家*/
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
    private SellerService sellerService;

    /**商家申请入驻*/
    @PostMapping("/save")
    public boolean save(@RequestBody Seller seller){
        try {
            //使用密码加密
            String password = new SimpleHash("md5", seller.getPassword(),seller.getSellerId(),5).toHex();
            //设置密码
            seller.setPassword(password);
            sellerService.save(seller);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
