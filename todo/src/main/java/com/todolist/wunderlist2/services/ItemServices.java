package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.model.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemServices {
    List<Item> findAllItems();

    Item findItemById(long itemid);

    Item saveNewItem(long categoryid, Item item);

    Item updateItem(long itemid, Item item);

    Item patchItem(Item item, long itemid);

    void deleteItem(long itemid);
}
