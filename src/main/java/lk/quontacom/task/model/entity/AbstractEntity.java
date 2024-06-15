package lk.quontacom.task.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean isDeleted;
}
