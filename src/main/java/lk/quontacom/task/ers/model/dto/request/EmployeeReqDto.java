package lk.quontacom.task.ers.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeReqDto {
        @NotNull(message = "First name is mandatory")
        private String firstName;
        @NotNull(message = "last name is mandatory")
        private String lastName;
        @NotNull(message = "gender is mandatory")
        @Pattern(regexp = "(MALE|FEMALE)", message = "Gender must be either MALE or FEMALE")
        private String gender;
        @NotNull(message = "birthday is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private String birthday;
        @NotNull(message = "hired day is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private  String hiredDate;
        @NotNull(message = "user is mandatory")
        private String user;
}
