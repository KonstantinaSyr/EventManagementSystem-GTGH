package com.team3.eventManagementSystem.models;

import java.time.LocalDate;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Organizer org1=new Organizer("Eleni", "Kouvalakidou", " elenik@gmail.com" , 12345,"myDescription ");
        Event event1=new Event( "latin Night", "dancing", "best night", 100, 02, 23, 0, 230, org1);
        ApprovalRequest aRequest=new ApprovalRequest("type",event1, org1, "comments");
        org1.makeApproveRequest(aRequest);
        //ApprovalRequest request1= org1.makeApproveRequest(" requestDetails", event1, "myComments");
        //ApprovalRequest request2= org1.makeApproveRequest(" requestDetails2", event1, "myComments2");
        Employee emp1=new Employee("empName", "empSurname", "emp@gmail.com");
        
        //returns true if the employee has the event, false if not(e.g. belongs to other employee)
        emp1. approveRequest(aRequest); 
        
        
    }

	}


