package com.team3.eventManagementSystem.eventManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventManagmentSystemApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(EventManagmentSystemApplication.class, args);
		/*
		@Autowired
		RequestService requestService;
		
		// Create Visitors, Employees, Organizers and Events.
		 
		Organizer o1 = new Organizer("Minerva", "McConnaghal", "MiMc08@nomail.com", 256314789, "Lecturer");
		Organizer o2 = new Organizer("Albus", "Dumbledore", "Albus001@nomail.com", 80632146, "Professor");
		Organizer o3 = new Organizer("Filius", "Flitwick", "FF85444ff@nomail.com", 98564123, "Teacher");
		Organizer o4 = new Organizer("Severus", "Snape", "potionsSeverus@nomail.com", 89541223, "Potion maker");

		Visitor v1 = new Visitor("Serius", "Black", "serius_black@nomail.com");
		Visitor v2 = new Visitor("Remus", "Loupin", "reeemuss3@nomail.com");
		Visitor v3 = new Visitor("Horatius", "Slackhorn", "hohoslack@nomail.com");
		Visitor v4 = new Visitor("Luna", "Lovegood", "LU_na_cracra@nomail.com");
		Visitor v5 = new Visitor("Harry", "Potter", "harryP.@nomail.com");
		Visitor v6 = new Visitor("Hermioni", "Granger", "hermioni16g@nomail.com");
		Visitor v7 = new Visitor("Ron", "Weasly", "crazyWeas673@nomail.com");
		Visitor v8 = new Visitor("Neville", "Longbottom", "nevillePlants08@nomail.com");
		Visitor v9 = new Visitor("Cho", "Chang", "choCha2369!@nomail.com");
		Visitor v10 = new Visitor("Lilly", "Evans", "LillEv@nomail.com");

		Employee em1 = new Employee("Konstantina", "Syropoulou", "email");
		Employee em2 = new Employee("Eleni", "Kouvalakidou", "email");
		Employee em3 = new Employee("Eleni", "Kagia", "email");

		Event e1 = new Event("How to train your dragon", "Training", "The first steps of training dragons", "Denmark",
				24, 4, 2, 2025, 11, o1);

		Event e2 = new Event("Dark Lord: his story", "The rise and fall of the Dark Lord",
				"Details about his rise, the war and his defeat", "Hogwarts", 9, 8, 3, 2025, 11, o1);

		Event e3 = new Event("Crazy sisters", "Music concert", "Let's celebrate together the launch of our new album",
				"Beaubatons", 25, 900, 10, 2025, 21, o2);

		Event e4 = new Event("How to train your dragon vol2", "Training advanced",
				"You can take part only if you have attended first class", "ldo", 5, 9, 50, 6, 2026, o2);

		Event e5 = new Event("Dance with us", "Latin dancing to great latin music",
				"Amazing musing and great spirits!!!", "ldo", 5, 9, 50, 6, 2026, o3);

		Event e6 = new Event("Singing with th stars", "Singing", "with the stars", "Stars venue", 5, 9, 50, 6, 2026,
				o3);

		Event e7 = new Event("Live laugh love", "Quotes", "Inspirational", "Stars venue", 5, 9, 50, 6, 2026, o4);

		Event e8 = new Event("Baking 101", "Cupcakes", "with sprinkles", "Stars venue", 5, 9, 50, 6, 2026, o4);

		// Add events to eventService list.
		EventService.createEvent(e1);
		EventService.createEvent(e2);
		EventService.createEvent(e3);
		EventService.createEvent(e4);
		EventService.createEvent(e5);
		EventService.createEvent(e6);
		EventService.createEvent(e7);
		EventService.createEvent(e8);

		// Create 2 new Events for Organizers.
		Event e9 = new Event("Cry", "856", "785", "ldo venue", 5, 9, 50, 6, 2026, o1);

		Event e10 = new Event("Cooking 101", "Kotopoulo", "with patates", "ldo venue", 5, 9, 50, 6, 2026, o2);

		// Make an ApprovalRequest to delete two events from 2 different Organizers
		ApprovalRequest r1 = new ApprovalRequest("create", e9, o1, "Please approve!!!!");
		ApprovalRequest r2 = new ApprovalRequest("create", e10, o2, "Please approve!!!!");
		ApprovalRequest r3 = new ApprovalRequest("delete", e5, o3, "Please DELETE!!!!");
		ApprovalRequest r4 = new ApprovalRequest("delete", e8, o4, "Please DELETE!!!!");

		// Send the ApprovalRequests to Employees.
		o1.makeApproveRequest(r1);
		o1.makeApproveRequest(r2);
		o1.makeApproveRequest(r3);
		o1.makeApproveRequest(r4);

		// Employees Check the requests.
		em1.approveRequest(r1);
		em2.approveRequest(r3);
		em3.rejectRequest(r4);

		// Visitors make Reservations
		v1.makeReservation("Singing with th stars");
		v2.makeReservation("Singing with th stars");
		v3.makeReservation("Singing with th stars");
		v4.makeReservation("Baking 101");
		v5.makeReservation("How to train your dragon vol2");
		v6.makeReservation("Dark Lord: his story");
		v7.makeReservation("Dark Lord: his story");
		v8.makeReservation("Singing with th stars");
		v9.makeReservation("Singing with th stars");
		v10.makeReservation("Singing with th stars");

		o1.showMyEvents();

		System.out.println("-------------------------Show Requests-------------------------");
		requestService.showRequests("pending");
		requestService.showRequests("accepted");
		requestService.showRequests("rejected");

		Path filePath = Paths.get("C:\\Users\\nenik\\Desktop\\team3\\eventManagementSystem\\event-agenta.txt");

		ArrayList<String> linestoWrite = new ArrayList();
		linestoWrite.add("______________________EVENT LIST______________________");
		linestoWrite.add(EventService.getAllEvents().toString());
		linestoWrite.add("______________________REQUEST LIST THAT NEED APPROVAL______________________");
		linestoWrite.add(requestService.showRequests("pending").toString());
		linestoWrite.add("______________________REQUEST LIST THAT HAVE BEEN ACCEPTED______________________");
		linestoWrite.add(requestService.showRequests("accepted").toString());

		try {
			Files.write(filePath, linestoWrite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("exception");
			e.printStackTrace();
		}*/
	}

}
