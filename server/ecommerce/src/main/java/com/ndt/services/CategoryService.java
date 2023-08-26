package com.ndt.services;

import com.ndt.pojo.Category;
import com.ndt.repositories.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return this.categoryRepository.getCategories();
    }
}
