package com.todolist.wunderlist2.repositortes;

import com.todolist.wunderlist2.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryReopsitory extends CrudRepository<Category,Long> {

    List<Category> findByTitleContainingIgnoringCase(String title);
}
