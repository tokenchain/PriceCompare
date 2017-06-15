package com.price.action;

import com.opensymphony.xwork2.ActionSupport;
import com.price.dto.SearchResultDTO;
import com.price.model.Product;
import com.price.service.SearchService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
System.out.println("keyword:" + keyword + "| page:" + page);
        List<Product> products = searchService.search(keyword, page, SIZE);
        if(products != null) {
            //有搜索结果
            int keywordCount = searchService.countKeyword(keyword);
            searchResultDTO.setProducts(products);
            searchResultDTO.setKeywordCount(keywordCount);
            for (Product p : products) {
                System.out.println(p);
            }
            System.out.println("相关商品总量： " + keywordCount);
        } else {
            //无搜索结果
            System.out.println("无搜索结果");
        }
        return "json";
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
