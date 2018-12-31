package com.lyl.controller;

import com.lyl.model.Active;
import com.lyl.model.Product;
import com.lyl.service.ActiveService;
import com.lyl.service.ProductService;
import com.lyl.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 潘淮  on 2018/12/13.<br>
 */
@Controller
public class LoginController {

    @Autowired
    private ActiveService activeService;
    @Autowired
    private ProductService productService;

    @RequestMapping("/index")
    public String index(Model model) {
        try {
            Active active = activeService.get();
            List<Product> products = productService.get(active.getId());
            if (products != null && !products.isEmpty()){
                model.addAttribute("start", products.get(0).getOrig().setScale(0,BigDecimal.ROUND_HALF_UP).toString());
                model.addAttribute("end", products.get(products.size() - 1).getOrig().setScale(0,BigDecimal.ROUND_HALF_UP).toString());
            }else {
                model.addAttribute("start", BigDecimal.ZERO.toString());
                model.addAttribute("end", BigDecimal.ZERO.toString());
            }
            model.addAttribute("active", active);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("/ticket")
    public String ticket(HttpServletRequest request,Model model){
        String state = request.getParameter("state");
        if (StringUtils.isNotBlank(state)) {
            List<Product> products = productService.get(Integer.valueOf(state));
            model.addAttribute("products", products);
            return "ticket";
        }else {
            return "redirect:/index";
        }
    }
}
