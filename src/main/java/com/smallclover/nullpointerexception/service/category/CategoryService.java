package com.smallclover.nullpointerexception.service.category;

import com.smallclover.nullpointerexception.model.Category;

import java.util.List;
import java.util.Map;

/**
 * @Author: Amadeus
 * @Date: 2020/7/9 21:47
 */
public interface CategoryService {

    List<Category> selectAllCategories();
    boolean insertCategory(String categoryName);
    Map<Long, String> getCategoryByArticleIds(List<Long> articleIds);
}
