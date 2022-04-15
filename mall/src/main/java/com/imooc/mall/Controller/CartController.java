package com.imooc.mall.Controller;

import com.imooc.mall.Service.ICartService;
import com.imooc.mall.consts.MallConst;
import com.imooc.mall.form.CartAddForm;
import com.imooc.mall.form.CartUpdateForm;
import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.CartVo;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.reflect.Method;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private ICartService cartService;
    @PostMapping("add")
    public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm, HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return cartService.add(user.getId(),cartAddForm);
    }
    @GetMapping("list")
    public ResponseVo<CartVo> list(HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return cartService.list(user.getId());
    }
    @PutMapping("update/{id}")
    public ResponseVo<CartVo> update(@PathVariable(value = "id") Integer productId,
                                     HttpSession session,
                                     @RequestBody CartUpdateForm form){
         User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return cartService.update(user.getId(),productId,form);
    }

    @DeleteMapping("delete/{id}")
    public ResponseVo<CartVo> delete(@PathVariable(value = "id") Integer productId,
                                     HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return cartService.delete(user.getId(),productId);
    }
    @GetMapping("sum")
    public ResponseVo<Integer> sum(HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return cartService.sum(user.getId());
    }
    @PutMapping("selectAll")
    public ResponseVo<CartVo> selectAll(HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return cartService.selectAll(user.getId());
    }
    @PutMapping("unSelectAll")
    public ResponseVo<CartVo> unSelectAll(HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return cartService.unSelectAll(user.getId());
    }
}
