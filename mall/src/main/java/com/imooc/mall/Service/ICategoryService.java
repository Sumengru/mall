package com.imooc.mall.Service;

import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;

import java.util.List;

public interface ICategoryService {
    ResponseVo<List<CategoryVo>> selectAll();
}
