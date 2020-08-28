package com.todolist.wunderlist2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todolist.wunderlist2.model.User;
import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Class to represent the complex primary key for UserRoles
 */
@Embeddable
public class RoleId implements Serializable
{

    private long user;

    private long role;

    public RoleId()
    {
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
    }


    public long getRole()
    {
        return role;
    }


    public void setRole(long role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        RoleId that = (RoleId) o;
        return user == that.user &&
                role == that.role;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
