package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.exceptions.ResourceNotFoundException;
import com.todolist.wunderlist2.model.Category;
import com.todolist.wunderlist2.model.Item;
import com.todolist.wunderlist2.repositortes.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "itemService")
public class ItemServicesImpl implements ItemServices {
    @Autowired
    private ItemRepository itemrepos;

    @Autowired
    private CategoryServices categoryServices;

    @Override
    public List<Item> findAllItems() {
        List<Item> list = new ArrayList<>();
        itemrepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Item findItemById(long itemid) {
        return itemrepos.findById(itemid)
                .orElseThrow(() -> new EntityNotFoundException("Item id: " + itemid + " was not found."));
    }

    @Transactional
    @Override
    public Item saveNewItem(long categoryid, Item item) {

        Category newCat = categoryServices.findCategoryId(categoryid);
        Item newItem = new Item(item.getName(), item.getDescription(), item.getDate(), item.getRepeat(),item.getDone(),item.isShared(), newCat);


        return itemrepos.save(newItem);
    }

    @Transactional
    @Override
    public Item patchItem(long itemid, Item item) {


        if(itemrepos.findById(itemid).isPresent()) {
            Item currentItem = findItemById(itemid);
            LocalDate newDate = LocalDate.parse(item.getDate());
            currentItem.setName(item.getName());
            currentItem.setDescription(item.getDescription());
            currentItem.setDue(newDate);
            currentItem.setRepeat(item.getRepeat());

            return itemrepos.save(currentItem);
        } else throw new ResourceNotFoundException("Item with id: " + itemid + " was not found.");

    }

    @Transactional
    @Override
    public Item updateItem(Item item, long itemid) {
        Item currentItem = itemrepos.findById(itemid)
                .orElseThrow(() -> new EntityNotFoundException("Item id: " + itemid + " was not found.")); // Change to ResponseNotFoundException


        if(item.getName() != null) currentItem.setName(item.getName());
        if(item.getDescription() != null) currentItem.setDescription(item.getDescription());
        if(item.getDate() != null) {
            LocalDate newLocalDate = LocalDate.parse(item.getDate());
            currentItem.setDue(newLocalDate);
        }
        if(item.getRepeat() != 0) currentItem.setRepeat(item.getRepeat());
        if(item.getCategory() != null) currentItem.setCategory(item.getCategory());

        return itemrepos.save(currentItem);
    }

    @Transactional
    @Override
    public void deleteItem(long itemid) {
        itemrepos.findById(itemid)
                .orElseThrow(() -> new EntityNotFoundException("Item id: " + itemid + " was not found.")); // Change to ResponseNotFoundException
        itemrepos.deleteById(itemid);
    }
}