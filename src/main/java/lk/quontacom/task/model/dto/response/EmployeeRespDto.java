package lk.quontacom.task.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRespDto {
    private String employee_id;
    private String first_name;
    private String last_name;
    private String gender;
    private Date birthday;
    private  Date hired_date;
    private String current_age_in_days;
    private String user_id;
}
