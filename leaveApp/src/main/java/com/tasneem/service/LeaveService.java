package com.tasneem.service;

import com.tasneem.model.Leave;
import java.util.List;

public interface LeaveService {

  public Leave saveOrUpdate(com.tasneem.model.Leave leave);
  public List<Leave> findByEmpName(String name);
  public Leave findById(int id);
  public List<Leave> findAvailableLeaves();

}
