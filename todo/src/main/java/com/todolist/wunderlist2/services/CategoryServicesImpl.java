package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.exceptions.ResourceNotFoundException;
import com.todolist.wunderlist2.model.Category;
import com.todolist.wunderlist2.model.User;
import com.todolist.wunderlist2.repositortes.CategoryReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "todolistService")
public class CategoryServicesImpl implements CategoryServices{

    @Autowired
    private CategoryReopsitory categoryReopsitory;

    @Autowired
    private UserService userService;

    @Override
    public List<Category> findAllCategories() {
        List<Category> list = new ArrayList<>();
        categoryReopsitory.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public List<Category> findLikeTitle(String title)
    {
        List <Category> c = new ArrayList<>();
        categoryReopsitory.findByTitleIgnoringCase(title)
                .iterator()
                .forEachRemaining(c::add);
       return c;
    }

    @Override
    public Category findCategoryId(long categoryid) {
        return categoryReopsitory.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Todo List id: " + categoryid + " was not found."));
    }

    @Transactional
    @Override
    public Category saveCategory(long userid, String title) {
        Category newCat = new Category();

        if(newCat.getCategoryid() != 0) {
            categoryReopsitory.findById(newCat.getCategoryid())
                    .orElseThrow(() -> new ResourceNotFoundException("Todo List id: " + newCat.getCategoryid() + " was not found."));
        }

        User currentUser = userService.findUser(userid);
        newCat.setTitle(title);
        newCat.setUser(currentUser);

        return categoryReopsitory.save(newCat);
    }

    @Transactional
    @Override
    public Category updateCategories(long categoryid, String title) {
        if(categoryReopsitory.findById(categoryid).isPresent()) {
            Category updatedcat = findCategoryId(categoryid); updatedcat.setTitle(title);
            return categoryReopsitory.save(updatedcat);
        } else throw new ResourceNotFoundException("Todo List with id: " + categoryid + " was not found.");
    }

    @Transactional
    @Override
    public void delete(long categoryid) {
        categoryReopsitory.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Todo List id: " + categoryid + " was not found."));
        categoryReopsitory.deleteById(categoryid);
    }

    @Transactional
    @Override
    public Category save(Category finalCat) {
        Category newCat = new Category();

        return categoryReopsitory.save(finalCat);
    }
}

