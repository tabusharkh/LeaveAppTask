package com.leaveapp.controller;

import com.leaveapp.service.LeaveService;
import com.leaveapp.model.Leave;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LeaveAppController {

  @Autowired
  private LeaveService leaveService;

  @GetMapping("/")
  public String getIndex() {
    return "login";
  }

  @GetMapping("/secure/emp")
  public String getEmp(Model model) {
    model.addAttribute("leave", new Leave());
    return "leaves";
  }

  @GetMapping("/secure/man")
  public String getMan(Model model) {
    model.addAttribute("leaves", leaveService.getManagerLeaves());
    return "manager";
  }

  @PostMapping("/secure/newLeave")
  public String addNewLeave(@Valid Leave leave, BindingResult result, Model model) {
    if(result.hasErrors()) {
      return "leaves";
    }
    leaveService.requestNewLeave(leave);
    model.addAttribute("leave", new Leave());
    model.addAttribute("leaves", leaveService.getEmpLeaves());
    return "leaves";
  }

  @GetMapping("/secure/cancelLeave/{id}")
  public String cancelLeave(@PathVariable int id, Model model) {
    leaveService.cancelLeave(id);
    model.addAttribute("leave", new Leave());
    model.addAttribute("leaves", leaveService.getEmpLeaves());

    return "leaves";
  }

  @GetMapping("/secure/approveLeave/{id}")
  public String approveLeave(@PathVariable int id, Model model) {
    leaveService.approveLeave(id);
    model.addAttribute("leaves", leaveService.getManagerLeaves());
    return "manager";
  }

  @GetMapping("/secure/rejectLeave/{id}")
  public String rejectLeave(@PathVariable int id, Model model) {
    leaveService.rejectLeave(id);
    model.addAttribute("leaves", leaveService.getManagerLeaves());
    return "manager";
  }

}
