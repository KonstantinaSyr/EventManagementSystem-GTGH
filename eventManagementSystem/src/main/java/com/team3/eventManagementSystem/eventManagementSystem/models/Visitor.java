package com.team3.eventManagementSystem.eventManagementSystem.models;

import static com.team3.eventManagementSystem.eventManagementSystem.service.EventService.*;

public class Visitor {
	
	private String name;
	private String surname;
	private String email;
	
	//Constructor
	public Visitor(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	//Searches for a certain event and prints its information.
    public void searchEvent(String title){
        Event event = findEventByTitle(title);
        if(event != null){
            System.out.println(event);
        }
    }
    
    /*Uses the function service.EventService.viewExistingEvents()
    that prints all the existing events
     */
    public void viewEvents(){
        viewExistingEvents();
    }
    
    /*The function makes a reservation for the visitor for a certain event, 
     * given by its title, by using the function approveReservation 
     */
    public void makeReservation(String title){
        Event event = findEventByTitle(title);
        if(event != null){
            Reservation.approveReservation(this, event);
        }
    }
    
    /*If the visitor has reserved a spot for the given event it returns information about the event,
    else it returns the appropriate message.
     */
    public void viewReservation(String title){
        Event event = findEventByTitle(title);
        if(Reservation.checkIfNotExists(this, event)){
            System.out.println("You have not reserved a spot for the event " + title + "!!!");
        }
        else{
            System.out.println("Here are some details for your reservation: ");
            searchEvent(title);
        }
    }
    
    /*The function cancels the visitor's reservation
    for a certain event, given by title
     */
    public void cancelReservation(String title){
        Event event = findEventByTitle(title);
        if(event != null){
            Reservation.removeReservation(this, event);
        }
    }
    
    
  //GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
    
}
