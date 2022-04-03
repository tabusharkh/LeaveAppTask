package com.tasneem.controller;

import com.tasneem.model.Leave;
import com.tasneem.serviceImpl.LeaveServiceImpl;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class LeaveController {

  @Autowired
  private LeaveServiceImpl leaveService;

  @GetMapping("/appLogin")
  public String login() {

    return "login";
  }

  @PostMapping("/api/newLeave")
  public ResponseEntity<Leave> leaveRequest(@RequestBody Leave leave) {
    //String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/newLeave").toUriString());
    return ResponseEntity.created(uri).body(leaveService.saveOrUpdate(leave));
  }

  @GetMapping("/api/getEmpLeaves/{empName}")
  public ResponseEntity<List<Leave>> getEmployeeLeaves(@PathVariable String empName) {
    System.out.println(empName);
    return ResponseEntity.ok().body(leaveService.findByEmpName(empName));
  }

  @GetMapping("/api/getAvailableLeaves")
  public ResponseEntity<List<Leave>> getEmployeeLeaves() {
    return ResponseEntity.ok().body(leaveService.findAvailableLeaves());
  }

  @GetMapping("/api/cancelLeave/{id}")
  public ResponseEntity<Leave> cancelLeave(@PathVariable("id") int id) {
    Leave temp = leaveService.findById(id);
    //To cancel a leave, we should update the status to be 1.
    temp.setStatus(1);
    return ResponseEntity.ok().body(leaveService.saveOrUpdate(temp));
  }

  @GetMapping("/api/approveLeave/{id}")
  public ResponseEntity<Leave> approveLeave(@PathVariable("id") String id) {
    int intId = Integer.parseInt(id);
    Leave temp = leaveService.findById(intId);
    //To approve a leave, we should update the status to be 2.
    temp.setStatus(2);
    return ResponseEntity.ok().body(leaveService.saveOrUpdate(temp));
  }

  @GetMapping("/api/rejectLeave/{id}")
  public ResponseEntity<Leave> rejectLeave(@PathVariable("id") int id) {
    Leave temp = leaveService.findById(id);
    //To reject a leave, we should update the status to be 3.
    temp.setStatus(3);
    return ResponseEntity.ok().body(leaveService.saveOrUpdate(temp));
  }
}
