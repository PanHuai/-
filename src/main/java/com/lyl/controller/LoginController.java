package com.lyl.controller;

import com.lyl.enums.CartEnum;
import com.lyl.enums.FlagEnum;
import com.lyl.model.Active;
import com.lyl.model.Cart;
import com.lyl.model.Product;
import com.lyl.model.User;
import com.lyl.service.ActiveService;
import com.lyl.service.CartService;
import com.lyl.service.ProductService;
import com.lyl.utils.Response;
import com.lyl.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    @Autowired
    private CartService cartService;

    @RequestMapping("/index")
    public String index(Model model) {
        try {
            Active active = activeService.get();
            List<Product> products = productService.getOrigByActive(active.getId());
            if (products != null && !products.isEmpty()){
                model.addAttribute("start", products.get(0).getOrig().setScale(0,BigDecimal.ROUND_HALF_UP).toString());
                model.addAttribute("end", products.get(products.size() - 1).getOrig().setScale(0,BigDecimal.ROUND_HALF_UP).toString());
            }else {
                model.addAttribute("start", BigDecimal.ZERO.toString());
                model.addAttribute("end", BigDecimal.ZERO.toString());
            }
            model.addAttribute("active", active);
            model.addAttribute("format", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping("/api/ticket")
    public String ticket(HttpServletRequest request,Model model){
        String active_id = request.getParameter("state");
        User user = (User) request.getSession().getAttribute("user");
        if (StringUtils.isNotBlank(active_id)) {
            List<Product> products = productService.getProductsByActive(Integer.valueOf(active_id));
            Active active = products.get(0).getActive();
            model.addAttribute("money",BigDecimal.ZERO.toString());
            List<Cart> carts = cartService.getByUserAndActive(user.getId(),active.getId());
            if (carts == null || carts.isEmpty()){
                carts = new ArrayList<>();
                for (Product product : products) {
                    Cart cart = new Cart();
                    cart.setActive(active);
                    cart.setUser(user);
                    cart.setBeginTime(LocalDateTime.now());
                    cart.setChecked(FlagEnum.FALSE.getCode());
                    cart.setCount(0);
                    cart.setState(CartEnum.OK.getCode());
                    cart.setProduct(product);
                    carts.add(cart);
                }
                cartService.add(carts);
            }else {
                Cart cart = cartService.getByUserActiveChecked(user.getId(), active.getId());
                if (cart != null){
                    Product product = cart.getProduct();
                    if (product.getType() == 1 && cart.getCount() < 3){
                        product = productService.getByTypeAndActive(active.getId(), 0);
                    }
                    BigDecimal money = product.getOrig().multiply(new BigDecimal(cart.getCount()));
                    model.addAttribute("money", money.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                }
            }
            model.addAttribute("products", products);
            model.addAttribute("carts", carts);
            model.addAttribute("active_id", active.getId());
            return "ticket";
        }else {
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "/api/miues",method = RequestMethod.POST)
    @ResponseBody
    public Response miues(HttpServletRequest request){
        Response response = new Response();
        try {
            User user = (User) request.getSession().getAttribute("user");
            int productId = Integer.valueOf(request.getParameter("productId"));
            int type = Integer.valueOf(request.getParameter("type"));
            int active_id = Integer.valueOf(request.getParameter("active_id"));
            Cart cart = cartService.getByUserAndProduct(user.getId(),productId);
            if (type == 0){
                cart.setCount(cart.getCount() - 1);
            }else if (type == 1){
                cart.setCount(cart.getCount() + 1);
            }
            if (cart.getCount() == 0){
                cart.setChecked(FlagEnum.FALSE.getCode());
            }else {
                cart.setChecked(FlagEnum.TRUE.getCode());
            }
            cart.setBeginTime(LocalDateTime.now());
            cartService.updateChecked(active_id,user.getId(),cart);
            response.setCode(200);
        }catch (Exception e){
            response.setCode(500);
        }
        return response;
    }

    @RequestMapping(value = "/api/cart/select",method = RequestMethod.POST)
    @ResponseBody
    public Response select(HttpServletRequest request){
        Response response = new Response();
        int cateId = Integer.valueOf(request.getParameter("cartId"));
        int active_id = Integer.valueOf(request.getParameter("active_id"));
        User user = (User) request.getSession().getAttribute("user");
        Cart cart = cartService.getById(cateId);
        cart.setChecked(FlagEnum.TRUE.getCode());
        cartService.updateChecked(active_id,user.getId(),cart);
        response.setCode(200);
        return response;
    }
}
