package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.exceptions.ResourceNotFoundException;
import com.todolist.wunderlist2.model.*;
import com.todolist.wunderlist2.repositortes.CategoryReopsitory;
import com.todolist.wunderlist2.repositortes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private CategoryReopsitory categoryReopsitory;


    @Autowired
    private RoleService roleService;

    // Getters

    public User findUser(long userid) throws
            ResourceNotFoundException {
        return userrepos.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + userid + " not found!"));
    }

    @Override
    public List<User> findnamelikeUser(String username)
    {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public User findUserByUsername(String username) {
        User u = userrepos.findByUsername(username);
        if (u == null) throw new ResourceNotFoundException("Username: " + username + " was not found.");

        return u;
    }


    @Override
    public List<User> findEveryUser() {
        List<User> list = new ArrayList<>();
        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    //Delete

    @Transactional
    @Override
    public void delete(long id) {
        userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    // Save
    @Transactional
    @Override
    public User save(User user) {
        User newUser = new User();

        if (user.getUserid() != 0) { userrepos.findById(user.getUserid()).orElseThrow(() -> new ResourceNotFoundException("User id: " + user.getUserid() + " was not found."));}

            newUser.setUsername(user.getUsername().toLowerCase());
            newUser.setPasswordNoEncrypt(user.getPassword());
            newUser.setUseremail(user.getUseremail().toLowerCase());
            System.out.println(user.getFirstname());
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());

            newUser.getCategories().clear();
            for (Category category : user.getCategories()) {
                Category newCat = new Category();
                newCat.setUser(newUser);
                newCat.setTitle(category.getTitle());

                newCat.getItems().clear();
                for (Item i : category.getItems()) {
                    Item createdItem = new Item();


                    createdItem.setName(i.getName());
                    createdItem.setDescription(i.getDescription());
                    createdItem.setDue(i.getDue());
                    createdItem.setRepeat(i.getRepeat());
                    createdItem.setCategory(newCat);

                    createdItem.setDone("In Progress");

                    newCat.getItems().add(createdItem);
                }

                newUser.getCategories().add(newCat);
            }



        return userrepos.save(newUser);
    }
   //Update
       @Transactional
        @Override
        public User update(User user, long userid)
         {
            User updatedUser = findUser(userid);

             if(user.getUsername() != null) updatedUser.setUsername(user.getUsername().toLowerCase());
            if(user.getPassword() != null) updatedUser.setPasswordNoEncrypt(user.getPassword());
             if(user.getUseremail() != null) updatedUser.setUseremail(user.getUseremail().toLowerCase());
             if(user.getFirstname() != null) updatedUser.setFirstname(user.getFirstname());
             if(user.getLastname() != null) updatedUser.setLastname(user.getLastname());


             if (user.getRoles()
                    .size() > 0) { updatedUser.getRoles().clear();
                for (UserRoles u : user.getRoles()) {
                    Role addRole = roleService.findRoleId(u.getRole().getRoleid());

                    updatedUser.getRoles().add(new UserRoles(updatedUser, addRole));
                }
            }

            return userrepos.save(updatedUser);


        }
    }


