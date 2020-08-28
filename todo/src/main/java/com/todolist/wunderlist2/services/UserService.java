package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.model.User;

import java.util.List;

public interface UserService {

        List<User> findEveryUser();

        User findUser(long userid);

        List<User> findnamelikeUser(String username);

        User findUserByUsername(String username);

        User save(User user);

        User update(User user, long userid);

        void delete(long userid);
}

