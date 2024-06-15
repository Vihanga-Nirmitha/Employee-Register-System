package lk.quontacom.task.model.dto.response;

import lk.quontacom.task.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDto {
    private  String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String role;
}
