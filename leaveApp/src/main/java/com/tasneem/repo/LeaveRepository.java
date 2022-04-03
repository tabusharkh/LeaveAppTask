package com.tasneem.repo;

import com.tasneem.model.Leave;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeaveRepository extends CrudRepository<Leave, Integer> {
  List<Leave> findByEmpName(String emp_name);
  Leave findById(int id);

  @Query(value = "SELECT * FROM leaves u WHERE u.status = 0", nativeQuery = true)
  List<Leave> findAvailableLeaves();
}
