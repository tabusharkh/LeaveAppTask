package com.leaveapp.model;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Leave {

  private String empName;

  @NotNull(message = "{Leave.FromDate.NotEmpty}")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date fromDate;

  @NotNull(message = "{Leave.ToDate.NotEmpty}")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date toDate;

  @NotEmpty(message = "{Leave.Reason.NotEmpty}")
  private String reason;

  private int status;

}
