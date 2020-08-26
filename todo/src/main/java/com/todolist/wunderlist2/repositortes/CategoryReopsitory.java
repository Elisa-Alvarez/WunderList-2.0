package com.todolist.wunderlist2.repositortes;

import com.todolist.wunderlist2.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryReopsitory extends CrudRepository<Category,Long> {
}
