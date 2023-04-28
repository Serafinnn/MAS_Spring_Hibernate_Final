package mas.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "employee_initiative")
public class EmployeeInitiative implements Serializable {
    @Id
    @ManyToOne
    private Employee employee;

    @Id
    @ManyToOne
    private Initiative initiative;

    private LocalDate assigned;

    public LocalDate getAssigned() {
        return assigned;
    }

    public void setAssigned(LocalDate assigned) {
        this.assigned = assigned;
    }

    public EmployeeInitiative(Employee employee, Initiative initiative, LocalDate assigned) {
        this.employee = employee;
        this.initiative = initiative;
        this.assigned = assigned;
    }

    public EmployeeInitiative(Employee employee, Initiative initiative) {
        this.employee = employee;
        this.initiative = initiative;
    }

    public EmployeeInitiative() {}

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    /**
     * Przykład asocjacji z atrybutem
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInitiative that = (EmployeeInitiative) o;
        return Objects.equals(employee, that.employee) && Objects.equals(initiative, that.initiative);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee, initiative);
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }
}
