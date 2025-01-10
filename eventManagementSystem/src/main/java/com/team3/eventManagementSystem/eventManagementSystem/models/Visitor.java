package com.team3.eventManagementSystem.eventManagementSystem.models;

import static com.team3.eventManagementSystem.eventManagementSystem.service.EventService.*;

public class Visitor {

    private String userName;
    private Integer id;
    private String name;
    private String surname;
    private String email;

    //Constructor
    public Visitor(String userName, String name, String surname, String email) {
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.id = 0;
    }

    public String toString(){
        return this.id +"." + this.name ;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
}

