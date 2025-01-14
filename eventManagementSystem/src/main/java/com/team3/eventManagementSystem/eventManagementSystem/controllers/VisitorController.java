package com.team3.eventManagementSystem.eventManagementSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team3.eventManagementSystem.eventManagementSystem.models.Visitor;
import com.team3.eventManagementSystem.eventManagementSystem.service.VisitorService;

@RestController
@RequestMapping("visitors")
public class VisitorController {

	@Autowired
	VisitorService visitorService;

	@GetMapping("/allVisitors")
	public List<Visitor> getAllVisitors() {
		return visitorService.getAllVisitors();
	}

	@PostMapping("/addVisitor")
	public List<Visitor> addVisitor(@RequestBody Visitor visitor) {
		return visitorService.addVisitor(visitor);
	}

	@DeleteMapping("/deleteVisitor")
	public List<Visitor> deleteVisitor(@RequestParam Integer id) {
		return visitorService.deleteVisitor(id);
	}

	@PutMapping("/putVisitor")
	public List<Visitor> updateVisitor(@RequestParam Integer id, String name, String surname, String email) {
		return visitorService.updateVisitor(id, name, surname, email);
	}

}
