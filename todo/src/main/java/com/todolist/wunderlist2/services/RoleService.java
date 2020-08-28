package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.model.Role;
import com.todolist.wunderlist2.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();


    Role findRoleId(long roleid);


    Role save(Role role);


    Role findByRole(String role);

    Role putUpdateRole(long roleid, Role role);


}
