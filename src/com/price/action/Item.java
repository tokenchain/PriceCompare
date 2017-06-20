package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.price.dto.FollowDTO;
import com.price.model.Product;
import com.price.model.User;
import com.price.service.FollowService;
import com.price.service.ProductService;
import com.price.util.CategoryUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("item")
@Scope("prototype")
public class Item extends ActionSupport implements ModelDriven {
    private long id;
    @Resource
    private ProductService productService;
    @Resource
    private FollowService followService;
    private FollowDTO followDTO;
    /*
    * 1代表收藏成功
    * 2代表收藏失败
    * 3代表取消收藏成功
    * 4代表取消收藏失败
    * 0代表其他错误
    * */
    private byte returnMsg;

    @Override
    public String execute() throws Exception {
        //获取商品信息
        Product product = productService.getProductDetailById(id);
        Map<String,Object> session = ActionContext.getContext().getSession();
        session.remove("productDetail");
        session.put("productDetail", product);
        //获取类别信息
        CategoryUtil categoryUtil = CategoryUtil.getInstance();
        CategoryUtil.Category[] category = categoryUtil.getCategoryById(product.getCategory_id());
        session.remove("productCategoryDetail");
        session.put("productCategoryDetail", category);
        //查询商品是否被收藏
        User user = (User)session.get("userInfo");
        if(user != null && followService.hasFollow(user.getId(), id)) {
            //被收藏
            session.remove("hasFollow");
            session.put("hasFollow", true);
        } else {
            //未被收藏
            session.remove("hasFollow");
            session.put("hasFollow", false);
        }
        return SUCCESS;
    }

    public String follow() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        User user = (User)session.get("userInfo");
        if (user == null || user.getId() == 0) {
            returnMsg = 0;
            return SUCCESS;
        }
        if(followDTO.isCancelFollow()) {
            //取消收藏
            if(followService.hasFollow(user.getId(), followDTO.getProductId())) {
                if (followService.delete(user.getId(), followDTO.getProductId())) {
                    //取消收藏成功
                    returnMsg = 3;
                } else {
                    returnMsg = 4;
                }
            } else {
                //商品不存在，取消收藏失败
                returnMsg = 4;
            }
        } else {
            //添加收藏
            Product product = (Product)session.get("productDetail");
            if(product == null || product.getId() == 0) {
                returnMsg = 0;
                return SUCCESS;
            }
            if(followService.hasFollow(user.getId(), followDTO.getProductId())) {
                //已经存在，收藏失败
                returnMsg = 2;
            } else {
                if(followService.save(user.getId(), followDTO.getProductId(), product.getLast_price())) {
                    //收藏成功
                    returnMsg = 1;
                } else {
                    returnMsg = 2;
                }
            }
        }
        return SUCCESS;
    }


    @Override
    public Object getModel() {
        if(followDTO == null) {
            followDTO = new FollowDTO();
        }
        return followDTO;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getReturnMsg() {
        return returnMsg;
    }
}
