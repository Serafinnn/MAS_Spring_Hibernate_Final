package mas.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Department implements Serializable {
    @Id
    @OrderColumn
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private java.lang.String name;
    private java.lang.String location;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();
    @ManyToOne
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        if (this.country != country){
            this.country.removeDeptQualification(this);
            this.country = country;
            country.addDeptQualification(this);
        }
    }

    public void removeCountry(){
        this.country.removeDeptQualification(this);
        this.country = null;
    }

    public Department() {}
    public Department(java.lang.String name, java.lang.String location) {
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getLocation() {
        return location;
    }

    public void setLocation(java.lang.String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
