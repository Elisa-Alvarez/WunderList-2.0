package com.todolist.wunderlist2.controllers;

import com.todolist.wunderlist2.model.Item;
import com.todolist.wunderlist2.services.ItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemServices itemService;

    // http://localhost:2020/items/itemlist

    @GetMapping(value = "/itemlist", produces = "application/json")
    public ResponseEntity<?> listAllItems() {
        List<Item> allItems = itemService.findAllItems();

        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

   //http://localhost:2020/items/:itemid

    @GetMapping(value = "/{itemid}", produces = "application/json")
    public ResponseEntity<?> listanItem(@PathVariable long itemid) {
        Item currentItem = itemService.findItemById(itemid);

        return new ResponseEntity<>(currentItem, HttpStatus.OK);
    }

   // http://localhost:2020/items/:categoryid

    @PostMapping(value = "/{categoryid}", consumes = "application/json")
    public ResponseEntity<?> saveNewItem(@PathVariable long categoryid, @Valid @RequestBody Item newitem) throws URISyntaxException {

        newitem = itemService.saveNewItem(categoryid, newitem);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newItemURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{itemid}")
                .buildAndExpand(newitem.getItemid())
                .toUri();
        responseHeaders.setLocation(newItemURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

     //http://localhost:2020/items/update/:itemid Replace an entire Item


    @PutMapping(value = "/update/{itemid}", consumes = "application/json")
    public ResponseEntity<?> updateItem(@Valid @RequestBody Item updateitem, @PathVariable long itemid) {
        return new ResponseEntity<>(updateitem,HttpStatus.OK);
    }

    // http://localhost:2020/items/patch/:itemid

    @PatchMapping(value = "/patch/{itemid}", consumes = "application/json")
    public ResponseEntity<?> patchItem(@RequestBody Item updateitem, @PathVariable long itemid) {
        itemService.patchItem(updateitem,itemid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // http://localhost:5280/items/:itemid

    @DeleteMapping(value = "/{itemid}")
    public ResponseEntity<?> deleteItem(@PathVariable long itemid) {
        itemService.deleteItem(itemid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
