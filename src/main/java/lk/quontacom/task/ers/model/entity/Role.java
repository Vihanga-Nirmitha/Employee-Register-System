package lk.quontacom.task.ers.model.entity;

import jakarta.persistence.*;
import lk.quontacom.task.ers.util.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor

public class Role extends AbstractEntity {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role(Long id, RoleType role) {
        this.id = id;
        this.role = role;
    }
}
