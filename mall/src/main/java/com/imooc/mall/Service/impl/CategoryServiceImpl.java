package com.imooc.mall.Service.impl;

import com.imooc.mall.Service.ICategoryService;
import com.imooc.mall.dao.CategoryMapper;
import com.imooc.mall.pojo.Category;
import com.imooc.mall.vo.CategoryVo;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.imooc.mall.consts.MallConst.ROOT_PARENT_ID;

@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();
//        List<CategoryVo> categoryVos = new ArrayList<>();
//        for (Category category : categories){
//            if(category.getParentId().equals(ROOT_PARENT_ID)){
//                CategoryVo categoryVo = new CategoryVo();
//                BeanUtils.copyProperties(category,categoryVo);
//                categoryVos.add(categoryVo);
//            }
//        }

        List<CategoryVo> categoryVos = categories.stream().filter(k -> k.getParentId().equals(ROOT_PARENT_ID))
                .map(this::category2CategoryVO)
                .collect(Collectors.toList());
        return ResponseVo.success(categoryVos);
    }

    public CategoryVo category2CategoryVO(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
