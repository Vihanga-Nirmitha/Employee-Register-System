package lk.quontacom.task.ers.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lk.quontacom.task.ers.util.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@NoArgsConstructor

public class Employee extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String first_name;
    @Column(nullable = false, length = 100)
    private String last_name;
    @Column(nullable = false)
    private LocalDateTime birthday;
    @Column(nullable = false)
    private LocalDateTime hired_date;
    private long current_age_in_days;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @ManyToOne
    @JsonBackReference
    private  User user;
}
