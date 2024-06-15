package lk.quontacom.task.ers.model.dto.response;

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
