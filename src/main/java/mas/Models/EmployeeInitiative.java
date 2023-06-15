package mas.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    public LocalDate getAssigned() {
        return assigned;
    }

    public void setAssigned(LocalDate assigned) {
        this.assigned = assigned;
    }

    public boolean getInitiativeLeader() {
        return initiativeLeader;
    }

    public void setInitiativeLeader(boolean initiativeLeader) {
        this.initiativeLeader = initiativeLeader;
    }

    @Override
    public String toString() {
        return "EmployeeInitiative{" +
                "id=" + id +
                ", employee=" + employee +
                ", initiative=" + initiative +
                ", assigned=" + assigned +
                ", initiativeLeader=" + initiativeLeader +
                '}';
    }
}
