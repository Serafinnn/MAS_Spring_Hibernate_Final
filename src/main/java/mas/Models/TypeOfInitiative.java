package mas.Models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Project")
public class TypeOfInitiative implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "typeOfSEQ")
    @SequenceGenerator(name = "typeOfSEQ", allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    @OneToMany
    private List<Initiative> initiatives = new ArrayList<>();


    public TypeOfInitiative(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TypeOfInitiative() {

    }

    public void addInitiative(Initiative initiative){
        if (!initiatives.contains(initiative)){
            initiatives.add(initiative);
            initiative.setTypeOfProject(this);
        }
    }
    public void removeInitiative(Initiative initiative){
        if (initiatives.contains(initiative)){
            initiatives.remove(initiative);
            initiative.setTypeOfProject(null);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Initiative> getInitiatives() {
        return initiatives;
    }

    public void printInitiativesSet(){
        for (Initiative p : initiatives){
            System.out.println(p);
        }
    }

}
