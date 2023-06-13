package mas.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Primary;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Initiative initiative;

    /**
     * Przykład history
     * */
    private LocalDate assigned;

    /**
     * Przykład subset
     * */
    private boolean initiativeLeader;


    public EmployeeInitiative(Employee employee, Initiative initiative, boolean isInitLeader) {
        this.employee = employee;
        this.initiative = initiative;
        this.assigned = LocalDate.now();
        this.initiativeLeader = isInitLeader;
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
