package lk.quontacom.task.ers.model.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReport {
    private int id;
    private String Full_name;
    private int age;
    private String sex;
    private Date Hired_date;

}
