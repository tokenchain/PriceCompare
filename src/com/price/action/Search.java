package com.price.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.price.dto.SearchResultDTO;
import com.price.model.Product;
import com.price.service.SearchService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("search")
@Scope("prototype")
public class Search extends ActionSupport {
    @Resource
    private SearchService searchService;
    private int page;
    private String keyword;
    private SearchResultDTO searchResultDTO = new SearchResultDTO();
    //page size
    private static int SIZE = 20;

    @Override
    public String execute() throws Exception {
//System.out.println("keyword:" + keyword + "| page:" + page);
        if(page < 1) {
            page = 1;
        }
        List<Product> products = searchService.search(keyword, page, SIZE);
        if(products != null && products.size() > 0) {
            //有搜索结果
            int keywordCount = 0;
            int totalPage = 0;
            if(products.size() > 1) {
                //不是通过Id搜索
                keywordCount = searchService.countKeyword(keyword);
                searchResultDTO.setKeywordCount(keywordCount);
                totalPage = (int)Math.ceil(keywordCount*1.0/SIZE);
            } else {
                //通过Id搜索
                searchResultDTO.setKeywordCount(1);
                totalPage = 1;
            }
            searchResultDTO.setTotalPage(totalPage);
            searchResultDTO.setProducts(products);
            //判断输入page是否大于实际最大page
            if(page <= totalPage) {
                searchResultDTO.setPage(page);
            } else {
                searchResultDTO.setPage(totalPage);
            }


            searchResultDTO.setKeyword(keyword);
            Map session = ActionContext.getContext().getSession();
            session.remove("products");
            session.put("products", searchResultDTO);
/*for (Product p : products) {
    System.out.println(p);
}
System.out.println("相关商品总量： " + keywordCount);*/
        } else {
            //无搜索结果
            Map session = ActionContext.getContext().getSession();
            session.remove("products");
            session.put("products",null);
        }
        return SUCCESS;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SearchResultDTO getSearchResultDTO() {
        return searchResultDTO;
    }
}
