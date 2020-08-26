package com.todolist.wunderlist2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="UserSharedItems")
public class UserSharedItem extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "roles", allowSetters = true)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "categories")
    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name = "items")
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private Item item;

    public UserSharedItem() {
    }

    public UserSharedItem(User user, Role role, Category category, Item item) {
        this.user = user;
        this.role = role;
        this.category = category;
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSharedItem)) return false;
        UserSharedItem that = (UserSharedItem) o;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
                ((role == null) ? 0 : role.getRoleid()) == ((that.role == null) ? 0 : that.role.getRoleid()) &&
                ((category == null) ? 0 : category.getCategoryid()) == ((that.category == null) ? 0 : that.category.getCategoryid()) &&
                ((item == null) ? 0 : item.getItemid()) == ((that.item == null) ? 0 : that.item.getItemid());
    }

    @Override
    public int hashCode() {
       // return Objects.hash(getUser(), getRole(), getCategory(), getItem());

        return 37;
    }
}
