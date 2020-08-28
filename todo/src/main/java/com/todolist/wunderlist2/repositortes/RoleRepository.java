package com.todolist.wunderlist2.repositortes;

import com.todolist.wunderlist2.model.Role;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface RoleRepository extends CrudRepository <Role, Long> {

    Role findByRole(String role);

    @Transactional
    @Modifying
    @Query(value = "UPDATE role SET role = :role, last_modified_by = :user , last_modified_date = CURRENT_TIMESTAMP WHERE roleid = :roleid", nativeQuery = true)
    void updateRole(String user, long roleid, String role);
}
