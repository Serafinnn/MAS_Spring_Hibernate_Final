package mas.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Initiative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    String name;
    String description;
    @OneToMany(
            mappedBy = "initiative",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<EmployeeInitiative> employees = new ArrayList<>();

    /**
     * Dziedziczenie wieloaspektowe
     * */
    @ManyToOne
    Framework type;
    @ManyToOne
    TypeOfInitiative typeOfInitiative;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Initiative(String name, String description, Framework framework) throws Exception {
        this.description = description;
        this.name = name;
        if (framework == null){
            throw new Exception("Framework cannot be null");
        }
        this.type = framework;
    }

    public Initiative() {
    }

    public Framework getFramework() {
        return type;
    }

    public void setFramework(Framework framework) {
        if (framework != null && framework != this.type){
            this.type.removeInit(this);
            this.type = framework;
            framework.addInit(this);
        }
    }

    public List<EmployeeInitiative> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeInitiative> employees) {
        this.employees = employees;
    }

    /**
     * przykład XOR
     * */
    public void setTypeOfProject(TypeOfInitiative type) {
        if (type != null && type != this.typeOfInitiative){
            this.typeOfInitiative.removeInitiative(this);
            this.typeOfInitiative = type;
            type.addInitiative(this);
        }
    }
}
