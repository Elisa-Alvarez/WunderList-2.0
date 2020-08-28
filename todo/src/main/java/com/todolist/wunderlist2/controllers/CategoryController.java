package com.todolist.wunderlist2.controllers;


import com.todolist.wunderlist2.model.Category;
import com.todolist.wunderlist2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todolist.wunderlist2.services.CategoryServices;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryServices categoryServices;


    // http://localhost:2020/categories/category

    @GetMapping(value = "/category", produces = "application/json")
    public ResponseEntity<?> listAllCategories() {
        List<Category> allCategories = categoryServices.findAllCategories();

        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    //http://localhost:2020/categories/:categories
    @GetMapping(value = "/{categoryid}", produces = "application/json")
    public ResponseEntity<?> findCategory(@PathVariable long categoryid) {
        Category category = categoryServices.findCategoryId(categoryid);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    // http://localhost:2020/categories/:letter
    @GetMapping(value = "/{letter}",produces = "application/json")
    public ResponseEntity<?> findLikeTitle (@PathVariable String letter)
    {
        List<Category> name = categoryServices.findLikeTitle(letter);
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
   // http://localhost:2020/categories/:userid/:title

    @PostMapping(value = "/{userid}/{title}", consumes = "application/json")
    public ResponseEntity<?> createTodo(@PathVariable long userid, @PathVariable String title) throws URISyntaxException {
        Category newCat = categoryServices.saveCategory(userid, title);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTodoURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{todoid}")
                .buildAndExpand(newCat.getCategoryid())
                .toUri();
        responseHeaders.setLocation(newTodoURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


   // http://localhost:2020/todos/:todoid/t/:title
    @PutMapping(value = "//{title}", consumes = "application/json")
    public ResponseEntity<?> updateTodo(@PathVariable long todoid, @PathVariable String title) {
        categoryServices.updateCategories(todoid, title);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // No PatchMapping needed since it'll literally be no different than the PutMapping above for this table

    /*
    Delete a Todolist and all of it's associated Items
    http://localhost:5280/todos/:todoid
    */
    @DeleteMapping(value = "/{todoid}")
    public ResponseEntity<?> deleteTodo(@PathVariable long todoid) {
        categoryServices.delete(todoid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
