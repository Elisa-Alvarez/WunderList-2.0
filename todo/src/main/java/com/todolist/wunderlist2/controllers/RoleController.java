package com.todolist.wunderlist2.controllers;

import com.todolist.wunderlist2.model.Role;
import com.todolist.wunderlist2.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/roles")
public class RoleController
{

    @Autowired
    RoleService roleService;

  //http://localhost:2020/roles/allroles
    @GetMapping(value = "/allroles",
            produces = "application/json")
    public ResponseEntity<?> listTypeOFRoles()
    {
        List<Role> allRoles = roleService.findAllRoles();
        return new ResponseEntity<>(allRoles,
                HttpStatus.OK);
    }

 //http://localhost:2020/roles/:roleid
    @GetMapping(value = "/{roleId}",
            produces = "application/json")
    public ResponseEntity<?> getRoleById(
            @PathVariable
                    Long roleId)
    {
        Role rid = roleService.findRoleId(roleId);
        return new ResponseEntity<>(rid,
                HttpStatus.OK);
    }


    @GetMapping(value = "/role/name/{roleName}",
            produces = "application/json")
    public ResponseEntity<?> findRole(
            @PathVariable
                    String roleName)
    {
        Role r = roleService.findByRole(roleName);
        return new ResponseEntity<>(r,
                HttpStatus.OK);
    }

    @PostMapping(value = "/role",
            consumes = "application/json")
    public ResponseEntity<?> addNewRole(
            @Valid
            @RequestBody
                    Role newRole)
    {

        newRole.setRoleid(0);
        newRole = roleService.save(newRole);


        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{roleid}")
                .buildAndExpand(newRole.getRoleid())
                .toUri();
        responseHeaders.setLocation(newRoleURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PutMapping(value = "/role/{roleid}",
            consumes = {"application/json"})
    public ResponseEntity<?> putUpdateRole(
            @PathVariable
                    long roleid,
            @Valid
            @RequestBody
                    Role newRole)
    {
        newRole = roleService.update(roleid,
                newRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
