package com.team3.eventManagementSystem.models;

import java.time.LocalDate;
import java.util.Date;

public class Main {

	public static void main(String[] args) {

		Organizer org1=new Organizer("Eleni", "Kouvalakidou", " elenik@gmail.com" , 12345,"myDescription ");
        Event event1=new Event( "latin Night", "dancing", "best night", 100, 02, 23, 0, 230, org1);
        Event event2=new Event( "Reggaeton Night", "dancing regg", "good night", 150, 02, 23, 0, 230, org1);

        
       // ApprovalRequest aRequest=new ApprovalRequest("type",event1, org1, "comments");
        ApprovalRequest request1= new ApprovalRequest(" requestDetails", event1,org1, "myComments");
        ApprovalRequest request2= new ApprovalRequest(" requestDetails2", event2, org1, "myComments2");
        org1.makeApproveRequest(request1);
        org1.makeApproveRequest(request2);
        Employee emp1=new Employee("empName", "empSurname", "emp@gmail.com");
        
        emp1.showRequests("pending");
        //returns true if the employee has the event, false if not(e.g. belongs to other employee)
        emp1. approveRequest(request1); 
        emp1.showEventListStatus();
        emp1.rejectRequest(request2);
               
    }

	}


