package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

public class VisitorService {


    private List<Visitor> visitorList = new ArrayList<>();
    private int nextKey = 1;

    public VisitorService() {
    }
    
    private boolean visitorExists(String userName) {
        return visitorList.stream()
                .anyMatch(visitor -> visitor.getUserName().equals(userName));
    }


    public void addNewVisitor(Visitor visitor){
        if(!visitorExists(visitor.getUserName())) {
            visitor.setId(nextKey);
            visitorList.add(visitor);
            System.out.println("Visitor added successfully");
            nextKey++ ;
        }
        else
            System.out.println("There already exists a visitor with that username");
    }
    
    public Visitor findVisitorByUserName(String userName){
        Visitor visitor = visitorList.stream()
                .filter(v -> v.getUserName().equals(userName))
                .findFirst()
                .orElse(null);

        if (visitor != null){
            return visitor;
        }
        else {
            System.out.println("Invalid user name provided. Please check again!");
            return null;
        }

    }
    
    public void deleteVisitor(String userName){
        Visitor visitor = findVisitorByUserName(userName);
        if(visitor != null){
            visitorList.remove(visitor);
            System.out.println("Visitor removed");
        }
    }
    
    public List<Visitor> getTotalVisitors(){
        return visitorList;
    }
    
    
}
