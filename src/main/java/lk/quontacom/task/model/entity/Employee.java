package lk.quontacom.task.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lk.quontacom.task.util.enums.Gender;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;
    @Column(nullable = false, length = 100)
    private String first_name;
    @Column(nullable = false, length = 100)
    private String last_name;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false)
    private Date hired_date;
    @Column(nullable = false)
    private int current_age_in_days;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @ManyToOne
    @JsonBackReference
    private  User user_id;
}
