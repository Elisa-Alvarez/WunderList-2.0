package com.todolist.wunderlist2.controllers;


import com.todolist.wunderlist2.model.Category;
import com.todolist.wunderlist2.model.Item;
import com.todolist.wunderlist2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todolist.wunderlist2.services.CategoryServices;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    @GetMapping(value = "/search/{letter}",produces = "application/json")
    public ResponseEntity<?> findLikeTitle (@PathVariable String letter)
    {
        List<Category> name = categoryServices.findLikeTitle(letter);
        return new ResponseEntity<>(name,HttpStatus.OK);
    }
   // http://localhost:2020/categories/:userid

    @PostMapping(value = "/{userid}", consumes = "application/json")
    public ResponseEntity<?> createTodo(@PathVariable long userid, @Valid @RequestBody Category category) throws URISyntaxException {

        Category newCat = categoryServices.saveCategory(userid,category.getTitle());

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTodoURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{todoid}")
                .buildAndExpand(newCat.getCategoryid())
                .toUri();
        responseHeaders.setLocation(newTodoURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


   // http://localhost:2020/categories/:categoryid/:title
    @PutMapping(value = "/{categoryid}/{title}", consumes = "application/json")
    public ResponseEntity<?> updateCategories(@PathVariable long categoryid, @PathVariable String title) {
        categoryServices.updateCategories(categoryid, title);

        return new ResponseEntity<>(HttpStatus.OK);
    }


   // http://localhost:5280/categories/:categoryid

    @DeleteMapping(value = "/{categoryid}")
    public ResponseEntity<?> delete(@PathVariable long categoryid) {
        categoryServices.delete(categoryid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
