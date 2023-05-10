package mas.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@JsonIgnoreProperties(value = "departmentQualification")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "countryGen", allocationSize = 1)
    int id;

    @NonNull
    String fullName;
    @NonNull
    String shortName;
    @NonNull
    float surfaceInMetersSquared;
    /**
     * Przykład asocjacji kwalifikowanej
     */
    @OneToMany
    @ToString.Exclude
    private Map<String, Department> departmentQualification = new TreeMap<>();

    public void addDeptQualification(Department department){
        if (!departmentQualification.containsKey(department.getName())){
            departmentQualification.put(department.getName(), department);
            department.setCountry(this);
        }
    }

    public void removeDeptQualification(Department department){
        if (!departmentQualification.containsKey(department.getName())){
            departmentQualification.remove(department.getName());
            department.removeCountry();
        }
    }

    public Department findDeptQualification(String name) throws Exception{
        if (!departmentQualification.containsKey(name))
            throw new Exception("Cannot find that department");
        return departmentQualification.get(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
