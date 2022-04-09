package com.imooc.mall.Controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.Service.IProductService;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCotroller {
    @Autowired
    private IProductService productService;
    @GetMapping("product")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false,defaultValue = "2") Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "1") Integer pageSize){
        return productService.list(categoryId, pageNum, pageSize);
    }
}
