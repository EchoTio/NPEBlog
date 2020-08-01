package com.smallclover.nullpointerexception.service.category.impl;

import com.smallclover.nullpointerexception.mapper.CategoryMapper;
import com.smallclover.nullpointerexception.model.Category;
import com.smallclover.nullpointerexception.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Amadeus
 * @Date: 2020/7/9 21:47
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    public List<Category> selectAllCategories(){
        return categoryMapper.selectAllCategories();
    }

    @Override
    public boolean insertCategory(String categoryName) {
        var category = new Category();
        category.setCategoryName(categoryName);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setDeleteFlag(false);
        long count =categoryMapper.insertCategory(category);
        return count >= 0;
    }
}
