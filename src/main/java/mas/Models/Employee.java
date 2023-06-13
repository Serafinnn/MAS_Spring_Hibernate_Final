package mas.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Employee extends Person implements Serializable{
    @NonNull
    private double salary;
    @NonNull
    private LocalDate empDate;
    @NonNull
    @ManyToOne
    private Department department;
    @NonNull
    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<EmployeeInitiative> initiatives = new ArrayList<>();

    /**
     * Przykład kompozycji (Pracownik nie może istnieć bez departamentu)
     */
    public Employee(String firstName, String lastName, LocalDate birthDate, double salary, LocalDate empDate, Department dept) throws Exception {
        super(firstName, lastName, birthDate);
        setSalary(salary);
        this.empDate = empDate;
        if (dept == null){
            throw new Exception();
        }
        this.department = dept;
        dept.getEmployees().add(this);
    }

    @Override
    public String getData() {
        return this.toString()+' '+getSalary();
    }

    public void addInit(Initiative initiative, boolean isInitLeader){
        EmployeeInitiative ep = new EmployeeInitiative(this, initiative, isInitLeader);
        initiatives.add(ep);
        initiative.getEmployees().add(ep);
    }

    public void removeInit(Initiative initiative){
        EmployeeInitiative ep = new EmployeeInitiative(this, initiative);
        initiative.getEmployees().remove(ep);
        initiatives.remove(ep);
        ep.setEmployee(null);
        ep.setInitiative(null);
    }

    public void setDepartment(Department department) throws Exception{
        if (department == null)
            throw new Exception();
        this.department.getEmployees().remove(this);
        this.department = department;
        department.getEmployees().add(this);
    }

    /**
     * Ograniczenia Atrybutów
     * */
    public void setSalary(double salary) throws Exception{
        if (salary > 20000){
            throw new Exception("Salary cannot exceed 20 000");
        }
        if (salary < 6000){
            throw new Exception("Salary must be higher than 6 000");
        }
        if (this.salary * 0.2d < Math.abs(salary - this.salary)){
            throw new Exception("Cannot change salary by more than 20%");
        }
        this.salary = salary;
    }

    @Override
    public java.lang.String toString() {
        return firstName + " " + lastName;
    }
}