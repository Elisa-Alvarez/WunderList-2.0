package com.todolist.wunderlist2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="items")
public class Item extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String done;


    @ManyToOne
    @JoinColumn(name = "categoryid",
            nullable = false)
    @JsonIgnoreProperties(value = "items",
            allowSetters = true)
    private Category category;




    public Item() {
    }

    public Item(String name, String description, String done, Category category) {
        this.name = name;
        this.description = description;
        this.done = done;
        this.category = category;
    }

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
