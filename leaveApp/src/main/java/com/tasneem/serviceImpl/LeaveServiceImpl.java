package com.tasneem.serviceImpl;

import com.tasneem.model.Leave;
import com.tasneem.repo.LeaveRepository;
import com.tasneem.service.LeaveService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

  @Service
  public class LeaveServiceImpl implements LeaveService {

  @Autowired
  LeaveRepository leaveRepository;

  public Leave saveOrUpdate(Leave leave) {
    return leaveRepository.save(leave);
  }

  public List<Leave> findByEmpName(String name) {
    return leaveRepository.findByEmpName(name);
  }

  public Leave findById(int id) {
    return leaveRepository.findById(id);
  }

  public List<Leave> findAvailableLeaves() {
    return leaveRepository.findAvailableLeaves();
  }
}
