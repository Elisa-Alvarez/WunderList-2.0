package com.todolist.wunderlist2.repositortes;

import com.todolist.wunderlist2.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long>
{
}
