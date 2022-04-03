package com.leaveapp.service;

import com.leaveapp.model.Leave;
import com.leaveapp.model.LeaveResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LeaveService {
  @Autowired
  private RestTemplate restTemplate;

  public String requestNewLeave(Leave leave) {
    leave.setEmpName(SecurityContextHolder.getContext().getAuthentication().getName());
    leave.setStatus(0);
    ResponseEntity<String> res = restTemplate.postForEntity(
        "http://localhost:8080/api/newLeave",
        leave,
        String.class
    );
    return res.getBody();
  }

  public LeaveResponse[] getEmpLeaves() {
    String empName = SecurityContextHolder.getContext().getAuthentication().getName();

    ResponseEntity<LeaveResponse[]> res = restTemplate.getForEntity(
        "http://localhost:8080/api/getEmpLeaves/{empName}",
        LeaveResponse[].class,
        empName);
    return res.getBody();
  }

  public LeaveResponse[] getManagerLeaves() {
    ResponseEntity<LeaveResponse[]> res = restTemplate.getForEntity(
        "http://localhost:8080/api/getAvailableLeaves",
        LeaveResponse[].class
    );
    return res.getBody();
  }

  public String cancelLeave(int id) {
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("id", id);
    ResponseEntity<String> res = restTemplate.postForEntity(
        "http://localhost:8080/api/cancelLeave/{id}",
        params,
        String.class
    );
    return res.getBody();
  }

  public String approveLeave(int id) {
    ResponseEntity<String> res = restTemplate.postForEntity(
        "http://localhost:8080/api/approveLeave/{id}",
        id,
        String.class
    );
    return res.getBody();
  }

  public String rejectLeave(int id) {
    ResponseEntity<String> res = restTemplate.postForEntity(
        "http://localhost:8080/api/rejectLeave/{id}",
        id,
        String.class
    );
    return res.getBody();
  }
}
