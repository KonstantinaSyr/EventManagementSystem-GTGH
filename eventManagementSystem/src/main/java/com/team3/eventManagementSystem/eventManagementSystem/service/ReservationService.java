package com.team3.eventManagementSystem.eventManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.team3.eventManagementSystem.eventManagementSystem.models.Event;
import com.team3.eventManagementSystem.eventManagementSystem.models.Reservation;
import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;

public class ReservationService {
	
	private VisitorService visitorServ = new VisitorService();
    private EventService eventServ = new EventService();
    private  List<Reservation> reservationsList = new ArrayList<>();

    public ReservationService(EventService eventServ, VisitorService visitorServ) {
        this.eventServ = eventServ;
        this.visitorServ = visitorServ;
    }

    //checks if a reservation exists in the list
    private boolean reservationExists(Visitor visitor , Event event){
        return reservationsList.stream()
                .anyMatch(reservation -> reservation.getVisitor().equals(visitor)
                        && reservation.getEvent().equals(event));
    }



    //creates a new reservation for an event
    public void createReservation(String userName , String eventTitle) {
        Visitor visitor = visitorServ.findVisitorByUserName(userName);
        Event event = eventServ.findEventByTitle(eventTitle);

        if(visitor != null && event != null){
            if(!reservationExists(visitor,event)){
                System.out.println("Reservation made");
                Reservation r = new Reservation(visitor, event);
                reservationsList.add(r);
            }
            else
                System.out.println("Reservation already exists");
        }
        else
            System.out.println("Incorrect credentials, please check again");
    }


    //prints some details about a visitor's already made reservation
    public void viewReservation(String userName, String eventTitle){
        Visitor visitor = visitorServ.findVisitorByUserName(userName);
        Event event = eventServ.findEventByTitle(eventTitle);

        if(visitor != null && event != null){
            if(!reservationExists(visitor,event)){
                System.out.println("You have not reserved a spot for the event " + eventTitle);
            }
            else{
                System.out.println("Your reservation for the event " + eventTitle + " has been secured already.");
                System.out.println("Here are some details for your event: ");
                System.out.println(eventServ.findEventByTitle(eventTitle));
            }
        }
        else
            System.out.println("Incorrect credentials, please check again");
    }



    //deletes a visitor's reservation
    public void deleteReservation(String userName, String eventTitle){
        Visitor visitor = visitorServ.findVisitorByUserName(userName);
        Event event = eventServ.findEventByTitle(eventTitle);

        if(visitor != null && event != null){
            if(!reservationExists(visitor,event)){
                System.out.println("You have not reserved a spot for the event " + eventTitle);
            }
            else {
                Reservation r = reservationsList.stream()
                        .filter(reservation -> reservation.getEvent().equals(event) &&
                                reservation.getVisitor().equals(visitor)).findAny()
                        .get();
                reservationsList.remove(r);
                System.out.println("Your reservation has been deleted");
            }
        }
        else
            System.out.println("The credentials given are wrong!! Check again!!!");
    }


 

    //returns the visitors that have enrolled in a specific event
    public void getVisitorsForEvent(String eventTitle) {
        Event event = eventServ.findEventByTitle(eventTitle);(event != null){
            List<String> nameList = reservationsList.stream()
                    .filter(reservation -> reservation.getEvent().equals(event))
                    .map(reservation -> reservation.getVisitor().getName()).toList();

            if(nameList.isEmpty()){
                System.out.println("No one has made a reservation for the event");
            }
            else {
                System.out.println("People who have reserved a spot are: ");
                nameList.forEach(System.out::println);
            }
        }
    }


    //returns all reservations for all events made (not the deleted reservations)
    public  List<Reservation> getAllReservations(){
        return reservationsList;
    }

}
