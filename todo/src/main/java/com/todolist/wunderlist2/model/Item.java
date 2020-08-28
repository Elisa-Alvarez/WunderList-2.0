package com.todolist.wunderlist2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate due;

    private String date;

    @Column(nullable = false)
    private String done;

    @Column(nullable = false)
    private int repeat;

    @Column(nullable = false)
    private boolean isShared;

    @ManyToOne
    @JoinColumn(name ="categoryid",nullable = false)
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private Category category;


    public Item() {
    }

    public Item(String name, String description, String date, int repeat, String done, boolean isShared, Category category) {
        this.name = name;
        this.description = description;
        this.done = done;
         this.due = LocalDate.parse(date);
         this.repeat= repeat;
        this.category = category;
        this.isShared = isShared;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDate getDue() {
        return due;
    }

    public void setDue(LocalDate due) {
        this.due = due;
    }

    public boolean isShared() {
        return isShared;
    }

    public void setShared(boolean shared) {
        this.isShared = isShared;
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
