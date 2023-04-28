package mas.Models;

import jakarta.persistence.Entity;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class CodeProject extends Initiative implements Serializable {

    private LocalDate startDate;
    private boolean completed;


    public CodeProject(String name, String description, LocalDate startDate, boolean completed, Framework framework) throws Exception {
        super(name, description, framework);
        this.startDate = startDate;
        this.completed = completed;

    }

    public CodeProject() {
        super();
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
