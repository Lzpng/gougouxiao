package com.gougouxiao.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gougouxiao.common.pojo.PageResult;
import com.gougouxiao.pojo.Brand;
import com.gougouxiao.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 品牌controller
 * <p>
 * //@RestController=@Controller+@ResponseBody
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    /**
     * 引用服务接口代理对象
     * Reference(timeout默认1000毫秒)
     */

    @Reference(timeout = 3000)
    private BrandService brandService;

    /**
     * 查询所有的品牌
     *
     * @return
     */
    @GetMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    /** 分页查询所以品牌 */
    @GetMapping("/findByPage")
    public PageResult findByPage( Brand brand, Integer page, @RequestParam(defaultValue = "10") Integer rows) {
        //get请求中文转码
     /*   System.out.println("brand = " + brand.getName());
        if (StringUtils.isNoneBlank(brand.getName())) {
            try {
                brand.setName(new String(brand.getName().getBytes("ISO8859-1"), "UTF-8"));
            }catch (Exception ex){
               ex.printStackTrace();
            }
        }*/

        return brandService.findByPage(brand, page, rows);
    }

    //添加品牌
    @PostMapping("/save")
    public boolean save(@RequestBody Brand brand) {
        try {
            brandService.save(brand);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //修改品牌
    @PostMapping("/update")
    public boolean update(@RequestBody Brand brand) {
        try {
            brandService.update(brand);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //删除品牌
    @GetMapping("/delete")
    public boolean delete(Long[] ids){
        try {
            brandService.deleteAll(ids);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    /**查询品牌数据*/
    @GetMapping("/findBrandList")
    public List<Map<String,Object>> findBrandList(){
        return brandService.findAllByIdAndName();
    }

}
