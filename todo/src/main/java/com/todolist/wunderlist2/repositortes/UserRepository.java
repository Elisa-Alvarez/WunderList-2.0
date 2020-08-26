package com.todolist.wunderlist2.repositortes;

import com.todolist.wunderlist2.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>
{
}
