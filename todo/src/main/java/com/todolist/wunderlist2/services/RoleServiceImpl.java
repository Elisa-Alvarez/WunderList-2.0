package com.todolist.wunderlist2.services;

import com.todolist.wunderlist2.exceptions.ResourceFound;
import com.todolist.wunderlist2.exceptions.ResourceNotFoundException;
import com.todolist.wunderlist2.model.Role;
import com.todolist.wunderlist2.repositortes.RoleRepository;
import com.todolist.wunderlist2.repositortes.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{

    @Autowired
    RoleRepository rolerepos;


    @Autowired
    UserRepository userrepos;


    @Autowired
    private UserAudit userAuditing;

    @Override
    public List<Role> findAllRoles()
    {
        List<Role> list = new ArrayList<>();

        rolerepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleId(long roleid)
    {
        return rolerepos.findById(roleid)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + roleid + " not found!"));
    }

    @Override
    public Role findByRole(String role)
    {
        Role roles = rolerepos.findByRole(role);

        if (roles != null)
        {
            return roles;
        } else
        {
            throw new ResourceNotFoundException(role);
        }
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        if (role.getUsers().size() > 0)
        {
            throw new ResourceFound("User Roles are not updated here, see documontion.");
        }

        return rolerepos.save(role);
    }



    @Transactional
    @Override
    public Role putUpdateRole(long roleid, Role role)
    {
        if (role.getRole() == null)
        {
            throw new ResourceNotFoundException("No role name found for the update!");
        }

        if (role.getUsers()
                .size() > 0)
        {
            throw new ResourceFound("User Roles are not updated here, see documentation for more info.");
        }

        Role newRole = findRoleId(roleid);
        newRole.setRole(newRole.getRole());
        return findRoleId(roleid);
    }
}
