package lk.quontacom.task.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lk.quontacom.task.util.enums.RoleType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @Column(nullable = false, length = 100)
    private String first_name;
    @Column(nullable = false, length = 100)
    private String last_name;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role_type;

    @ManyToOne
    @JsonBackReference
    private  Role role_id;

}
