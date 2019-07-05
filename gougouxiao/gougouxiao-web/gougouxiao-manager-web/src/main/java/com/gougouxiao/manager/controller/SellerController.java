package com.gougouxiao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.Seller;
import com.gougouxiao.service.SellerService;
import org.springframework.web.bind.annotation.*;

/**商家审核 */

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Reference
    private SellerService sellerService;


    /**多条件查询分页*/
    @GetMapping("/findByPage")
    public PageResult findByPage(Seller seller, Integer page,
                                 @RequestParam(defaultValue = "10")Integer rows){
            return sellerService.findByPage(seller, page, rows);
    }

    /**
     * 商家审核状态
     * sellerId //商家ID
     * status //商家状态
     * */
    @GetMapping("/updateStatus")
    public boolean updateStatus(String sellerId,String status){
        try {
            sellerService.updateStatus(sellerId,status);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }
}
