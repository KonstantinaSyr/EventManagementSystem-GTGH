package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Reservation {
	
	    private int visitorId;
	    private int eventId;

	    public Reservation(int visitorId, int eventId) {
	        this.visitorId = visitorId;
	        this.eventId = eventId;
	    }

	    public int getEventId() {
	        return this.eventId;
	    }

	    public int getVisitorId(){
	        return this.visitorId;
	    }
    

}
