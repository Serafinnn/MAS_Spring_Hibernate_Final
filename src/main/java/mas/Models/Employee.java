package mas.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Employee")
public class Employee extends Person implements Serializable{
    private double salary;
    private LocalDate empDate;
    @ManyToOne
    private Department department;
    @OneToMany(
            mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<EmployeeInitiative> initiatives = new ArrayList<>();

    /**
     * Przykład kompozycji (Pracownik nie może istnieć bez departamentu)
     */
    public Employee(String firstName, String lastName, LocalDate birthDate, double salary, LocalDate empDate, Department dept) throws Exception {
        super(firstName, lastName, birthDate);
        this.salary = salary;
        this.empDate = empDate;
        if (dept == null){
            throw new Exception();
        }
        this.department = dept;
        dept.getEmployees().add(this);
        personTraits.add(PersonTraits.Employee);
    }

    public Employee() {
        super();
    }

    @Override
    public String getData() {
        return this.toString()+' '+getSalary();
    }

    public void addInit(Initiative initiative){
        EmployeeInitiative ep = new EmployeeInitiative(this, initiative, LocalDate.now());
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


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getEmpDate() {
        return empDate;
    }

    public void setEmpDate(LocalDate empDate) {
        this.empDate = empDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) throws Exception{
        if (department == null)
            throw new Exception();
        this.department.getEmployees().remove(this);
        this.department = department;
        department.getEmployees().add(this);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}