package mas.ModelDTO;

import java.io.Serializable;


public class CodeProjectDto implements Serializable {
    String startDate;
    String completed;
    String name;
    String description;
    String type;
    String empName;

    public CodeProjectDto(String startDate, String completed, String name, String description, String type, String empName) {
        this.startDate = startDate;
        this.completed = completed;
        this.name = name;
        this.description = description;
        this.type = type;
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public java.lang.String getStartDate() {
        return startDate;
    }

    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }

    public java.lang.String getCompleted() {
        return completed;
    }

    public void setCompleted(java.lang.String completed) {
        this.completed = completed;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }


    @Override
    public java.lang.String toString() {
        return "CodeProjectDto{" +
                "startDate='" + startDate + '\'' +
                ", completed='" + completed + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", employee=" + empName +
                '}';
    }
}