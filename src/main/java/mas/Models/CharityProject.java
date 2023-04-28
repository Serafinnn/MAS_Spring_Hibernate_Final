package mas.Models;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class CharityProject extends Initiative implements Serializable {
    String nameOfOrg;


    public CharityProject(String name, String description, String nameOfOrg, Framework framework) throws Exception {
        super(name, description, framework);
        this.nameOfOrg = nameOfOrg;

    }

    public CharityProject() {

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

    public String getNameOfOrg() {
        return nameOfOrg;
    }

    public void setNameOfOrg(String nameOfOrg) {
        this.nameOfOrg = nameOfOrg;
    }
}
