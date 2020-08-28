package com.todolist.wunderlist2;

import com.todolist.wunderlist2.services.CategoryServices;
import com.todolist.wunderlist2.model.Category;
import com.todolist.wunderlist2.model.Item;
import com.todolist.wunderlist2.model.Role;
import com.todolist.wunderlist2.model.User;
import com.todolist.wunderlist2.repositortes.CategoryReopsitory;
import com.todolist.wunderlist2.repositortes.ItemRepository;
import com.todolist.wunderlist2.services.ItemServices;
import com.todolist.wunderlist2.services.RoleService;
import com.todolist.wunderlist2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/*
@Transactional
@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    CategoryServices categoryServices;

    @Autowired
    ItemServices itemServices;

    @Autowired
    RoleService roleService;

    @Autowired
    ItemRepository itemrepos;

    @Autowired
    CategoryReopsitory categoryReopsitory;

    @Transactional
    @Override
    public void run(String[] args) throws Exception {


       Role r1= new Role("USER");
       Role r2 = new Role("CO_USER");


        r1 = roleService.save(r1);
        r2 = roleService.save(r2);


        User u1 = new User("admin","password" , "lambda@gmail.com", "Java" , "Backend");
        User u2 = new User("hardworker", "ilovemyjob", "lovework@gmail.com", "CEO", "Workmore");
        User u3 = new User("millenial", "IwillbeMe", "Iamamillenial@gmail.com", "Abby", "Rugrat");
        User u4 = new User("bird12", "parrot", "birdsofafeather@gmail.com", "Sora", "Tailwind");

        Category c1 = new Category("Finish WonderList",u1);
        Category c2 = new Category("Ceo Goals ",u2 );
        Category c3 = new Category("Monopooly Winner",u3 );
        Category c4 = new Category("Bird Watching",u4 );


        Item i1 = new Item("Get Stuff done", "create app","2022-06-15", 1, "In Progress", false,c1 );
        Item i2 = new Item("What I Need","Clothes,soap,Pasta", "2020-12-02",2, "In Progress", false,c2);
        Item i3 = new Item("My Weekly Goals","Vision Board", "2020-01-21",1, "In Progress", false, c3);
        Item i4 = new Item("What I Need","Seeds", "2020-09-11",1, "In Progress", false, c4);

        c1.getItems().add(i1);
        c2.getItems().add(i2);
        c3.getItems().add(i3);
        c4.getItems().add(i4);

        u1.getCategories().add(c1);
        u2.getCategories().add(c2);
        u3.getCategories().add(c3);
        u4.getCategories().add(c4);
        System.out.println(u1.getFirstname());
        u1 = userService.save(u1);
        u2 = userService.save(u2);
        u3 = userService.save(u3);
        u4 = userService.save(u4);


    }
}
*/
