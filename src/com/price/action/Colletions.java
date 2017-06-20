package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.dto.FollowProductDTO;
import com.price.model.Follow;
import com.price.model.Product;
import com.price.model.User;
import com.price.service.FollowService;
import com.price.service.ProductService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("colletions")
@Scope("prototype")
public class Colletions extends ActionSupport implements ModelDriven {
    @Resource
    private FollowService followService;
    @Resource
    private ProductService productService;
    List<FollowProductDTO> followProductList;

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        User user = (User)session.get("userInfo");
        if(user == null || user.getId() == 0) {
            return ERROR;
        }
        //获取用户收藏商品信息
        List<Follow> follows = followService.getFollowDetailByUserId(user.getId());
        List<Long> productIds = new ArrayList<>(follows.size());
        for(Follow f : follows) {
            productIds.add(f.getProductId());
        }
        //获取商品当前价格
        List<Product> products = productService.getProductDetailByIds(productIds);
        Map<Long, Product> productMap = new HashMap<>(products.size());
        for(Product product : products) {
            productMap.put(product.getId(), product);
        }
        followProductList = new ArrayList<>(follows.size());
        for(int i = 0; i < follows.size(); i++) {
            Follow follow = follows.get(i);
            Product product = productMap.get(follow.getProductId());
            followProductList.add(new FollowProductDTO(follow.getProductId(), product.getName(), follow.getDate(), product.getLast_price(), follow.getPrice() - product.getLast_price()));
        }
        session.remove("followProductList");
        session.put("followProductList", followProductList);
        return SUCCESS;
    }

    @Override
    public Object getModel() {
        return null;
    }
}
