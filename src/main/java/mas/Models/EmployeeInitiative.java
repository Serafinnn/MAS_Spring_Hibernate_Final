package mas.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeInitiative implements Serializable {
    @Id
    @ManyToOne
    private Employee employee;
    @Id
    @ManyToOne
    private Initiative initiative;

    /**
     * Przykład history
     * */
    private LocalDate assigned;

    /**
     * Przykład subset
     * */
    private boolean iInitiativeLeader;


    public EmployeeInitiative(Employee employee, Initiative initiative, boolean isInitLeader) {
        this.employee = employee;
        this.initiative = initiative;
        this.assigned = LocalDate.now();
        this.iInitiativeLeader = isInitLeader;
    }

    public EmployeeInitiative(Employee employee, Initiative initiative) {
        this.employee = employee;
        this.initiative = initiative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeInitiative that = (EmployeeInitiative) o;
        return getEmployee() != null && Objects.equals(getEmployee(), that.getEmployee());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
