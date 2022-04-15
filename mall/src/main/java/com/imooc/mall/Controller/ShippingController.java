package com.imooc.mall.Controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.Service.IShippingService;
import com.imooc.mall.consts.MallConst;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.pojo.User;
import com.imooc.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("shipping")
public class ShippingController {
    @Autowired
    private IShippingService shippingService;
    @PostMapping("add")
    public ResponseVo<Map<String, Integer>> add(HttpSession session,
                                                @RequestBody ShippingForm form){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        ResponseVo<Map<String, Integer>> mapResponseVo = shippingService.add(user.getId(), form);
        return mapResponseVo;
    }
    @DeleteMapping("delete/{id}")
    public ResponseVo delete(HttpSession session, @PathVariable(value = "id") Integer shippingId){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.delete(user.getId(),shippingId);
    }
    @PutMapping("update/{id}")
    public ResponseVo update( HttpSession session,
                              @PathVariable(value = "id") Integer shippingId,
                              @RequestBody ShippingForm form){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.update(user.getId(),shippingId,form);
    }
    @GetMapping("list")
    public ResponseVo<PageInfo> list(Integer pageNum, Integer pageSize,HttpSession session){
        User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return shippingService.list(pageNum,pageSize,user.getId());
    }
}
