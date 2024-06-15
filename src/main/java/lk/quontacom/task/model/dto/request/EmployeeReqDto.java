package lk.quontacom.task.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeReqDto {
        @NotNull(message = "First name is mandatory")
        private String first_name;
        @NotNull(message = "last name is mandatory")
        private String last_name;
        @NotNull(message = "gender is mandatory")
        @Pattern(regexp = "(MALE|FEMALE)", message = "Gender must be either MALE or FEMALE")
        private String gender;
        @NotNull(message = "birthday is mandatory")
        @DateTimeFormat
        private String birthday;
        @NotNull(message = "hired day is mandatory")
        @DateTimeFormat
        private  String hired_date;
        @NotNull(message = "user is mandatory")
        private String user_id;
}
