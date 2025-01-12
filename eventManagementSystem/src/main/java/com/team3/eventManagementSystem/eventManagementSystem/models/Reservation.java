package com.team3.eventManagementSystem.eventManagementSystem.models;

public class Reservation {
	
	    private Integer visitorId;
	    private Integer eventId;

	    public Reservation(int visitorId, int eventId) {
	        this.visitorId = visitorId;
	        this.eventId = eventId;
	    }

	    public Integer getEventId() {
	        return this.eventId;
	    }

	    public Integer getVisitorId(){
	        return this.visitorId;
	    }
    

}
