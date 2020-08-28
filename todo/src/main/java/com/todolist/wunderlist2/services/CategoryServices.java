package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.model.Category;

import java.util.List;

public interface CategoryServices {
    List<Category> findAllCategories();

     Category findCategoryId(long categoryid);

     List<Category> findLikeTitle(String title);

    Category saveCategory(long userid, String title);

    Category updateCategories(long categoryid, String title);

    void delete(long categoryid);

}
