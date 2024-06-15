package lk.quontacom.task.ers.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReqDto {
    @NotNull(message = "First name is mandatory")
    private String first_name;
    @NotNull(message = "last name is mandatory")
    private String last_name;
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email pattern")
    private String email;
    @NotNull(message = "password is mandatory")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password should contains At least one number , lower case, upper case and special character")
    private String password;
    @Pattern(regexp = "(USER|ADMIN)", message = "role must be either USER or ADMIN")
    private String role;

}
