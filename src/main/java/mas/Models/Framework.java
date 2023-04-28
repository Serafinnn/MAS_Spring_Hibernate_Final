package mas.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "framework")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Framework {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "frameworkGen")
    @SequenceGenerator(name = "frameworkGen", allocationSize = 1)
    private int id;
    String name;
    String company;
    @OneToMany(mappedBy = "type")
    List<Initiative> initiatives;

    public Framework(String name, String company){
        this.company = company;
        this.name = name;
        this.initiatives = new ArrayList<>();
    }

    public Framework() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Initiative> getInitiatives() {
        return initiatives;
    }

    public void addInit(Initiative initiative) {
        if (!this.initiatives.contains(initiative)){
            this.initiatives.add(initiative);
            initiative.setFramework(this);
        }
    }

    public void removeInit(Initiative initiative){
        this.initiatives.remove(initiative);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
