package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.price.model.Product;
import com.price.service.ProductService;
import com.price.util.CategoryUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("item")
@Scope("prototype")
public class Item extends ActionSupport {
    private long id;
    @Resource
    private ProductService productService;

    @Override
    public String execute() throws Exception {
        Product product = productService.getProductDetailById(id);
        Map<String,Object> session = ActionContext.getContext().getSession();
        session.remove("productDetail");
        session.put("productDetail", product);
        CategoryUtil categoryUtil = CategoryUtil.getInstance();
        CategoryUtil.Category[] category = categoryUtil.getCategoryById(product.getCategory_id());
        session.remove("productCategoryDetail");
        session.put("productCategoryDetail", category);
        return SUCCESS;
    }

    public void setId(long id) {
        this.id = id;
    }
}
