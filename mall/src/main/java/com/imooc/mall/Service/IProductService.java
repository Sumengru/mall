package com.imooc.mall.Service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.vo.ResponseVo;

public interface IProductService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);
}
