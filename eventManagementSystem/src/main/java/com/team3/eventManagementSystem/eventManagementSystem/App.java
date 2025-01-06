package com.team3.eventManagementSystem.eventManagementSystem;

import com.team3.eventManagementSystem.models.ApprovalRequest;
import com.team3.eventManagementSystem.models.Employee;
import com.team3.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.models.Organizer;
import com.team3.eventManagementSystem.models.Visitor;
import com.team3.eventManagementSystem.service.EventService;
import com.team3.eventManagementSystem.service.RequestService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
    	Organizer o1 = new Organizer("Minerva", "McConnaghal" , "MiMc08@nomail.com" ,256314789, "Lecturer");
        Organizer o2 = new Organizer("Albus","Dumbledore","Albus001@nomail.com",80632146,"Professor");
        Organizer o3 = new Organizer("Filius","Flitwick","FF85444ff@nomail.com", 98564123, "Teacher");
        Organizer o4 = new Organizer("Severus","Snape","potionsSeverus@nomail.com",89541223, "Potion maker");

        Visitor v1 = new Visitor("Serius", "Black" , "serius_black@nomail.com");
        Visitor v2 = new Visitor("Remus" , "Loupin" ,"reeemuss3@nomail.com");
        Visitor v3 = new Visitor("Horatius","Slackhorn","hohoslack@nomail.com");
        Visitor v4 = new Visitor("Luna","Lovegood","LU_na_cracra@nomail.com");
        Visitor v5 = new Visitor("Harry","Potter","harryP.@nomail.com");
        Visitor v6 = new Visitor("Hermioni","Granger","hermioni16g@nomail.com");
        Visitor v7 = new Visitor("Ron","Weasly","crazyWeas673@nomail.com");
        Visitor v8 = new Visitor("Neville","Longbottom","nevillePlants08@nomail.com");
        Visitor v9 = new Visitor("Cho","Chang","choCha2369!@nomail.com");
        Visitor v10 = new Visitor("Lilly", "Evans","LillEv@nomail.com");

        Employee em1 = new Employee("Konstantina", "Syropoulou", "email");
        Employee em2 = new Employee("Eleni", "Kouvalakidou" , "email");
        Employee em3 = new Employee("Eleni","Kagia", "email");

        Event e1 = new Event("How to train your dragon","Training","The first steps of training dragons" ,
                "Denmark" ,24, 4,2,2025,11, o1);

        Event e2 = new Event("Dark Lord: his story","The rise and fall of the Dark Lord","Details about his rise, the war and his defeat" ,
                "Hogwarts" ,9, 8,3,2025,11, o1);

        Event e3 = new Event("Crazy sisters" , "Music concert", "Let's celebrate together the launch of our new album",
                "Beaubatons", 25,900,10,2025,21, o2);

        Event e4 = new Event("How to train your dragon vol2" ,"Training advanced" ,"You can take part only if you have attended first class",
                "ldo",5 , 9,50,6,2026,o2);

        Event e5 = new Event("Dance with us" ,"Latin dancing to great latin music" ,"Amazing musing and great spirits!!!",
                "ldo",5 , 9,50,6,2026,o3);

        Event e6 = new Event("Singing with th stars" ,"Singing" ,"with the stars", "Stars venue",5 , 9,50,6,2026,o3);

        Event e7 = new Event("Live laugh love" ,"Quotes" ,"Inspirational", "Stars venue",5 , 9,50,6,2026,o4);

        Event e8 = new Event("Baking 101" ,"Cupcakes" ,"with sprinkles", "Stars venue",5 , 9,50,6,2026,o4);


        EventService.createEvent(e1);
        EventService.createEvent(e2);
        EventService.createEvent(e3);
        EventService.createEvent(e4);
        EventService.createEvent(e5);
        EventService.createEvent(e6);
        EventService.createEvent(e7);
        EventService.createEvent(e8);


        Event e9 = new Event("Cry" ,"856" ,"785","ldo venue",5 , 9,50,6,2026,o1);

        Event e10 = new Event("Cooking 101" ,"Kotopoulo" ,"with patates","ldo venue",5 , 9,50,6,2026,o1);

        ApprovalRequest r1 = new ApprovalRequest("create", e9, o1, "Please approve!!!!"  );
        ApprovalRequest r2 = new ApprovalRequest("create", e10, o1, "Please approve!!!!"  );
        ApprovalRequest r3 = new ApprovalRequest("delete", e1, o1, "Please DELETE!!!!"  );
        ApprovalRequest r4 = new ApprovalRequest("delete", e2, o1, "Please DELETE!!!!"  );

 
        
        o1.makeApproveRequest(r1);
        o1.makeApproveRequest(r2);
        o1.makeApproveRequest(r3);
        o1.makeApproveRequest(r4);
        
        em1.approveRequest(r1);
        em2.approveRequest(r3);
        em3.rejectRequest(r4);

        
        v1.makeReservation("Singing with th stars");
        v2.makeReservation("Singing with th stars");
        v3.makeReservation("Singing with th stars");
        v4.makeReservation("Baking 101");
        v5.makeReservation("How to train your dragon vol2");
        v6.makeReservation("Dark Lord: his story");
        o2.makeReservation("Dark Lord: his story");

        o1.showMyEvents();

        RequestService.showRequests("pending");
        RequestService.showRequests("accepted");
        RequestService.showRequests("rejected"); 
    }
}
