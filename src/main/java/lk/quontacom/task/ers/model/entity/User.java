package lk.quontacom.task.ers.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor

public class User extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String first_name;
    @Column(nullable = false, length = 100)
    private String last_name;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @OneToMany( mappedBy = "user")
    private List<Employee> employees;
    @ManyToOne
    @JsonBackReference
    private  Role role;

}
