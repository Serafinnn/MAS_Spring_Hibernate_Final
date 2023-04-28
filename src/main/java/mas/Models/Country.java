package mas.Models;

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
@Table(name = "Country")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countryGen")
    @SequenceGenerator(name = "countryGen", allocationSize = 1)
    private int id;

    @NonNull
    private String fullName;
    private String shortName;
    private float surfaceInMetersSquared;
    /**
     * Przykład asocjacji kwalifikowanej
     */
    @OneToMany
    @ToString.Exclude
    private Map<String, Department> departmentQualification = new TreeMap<>();

    public Country(@NonNull String fullName, float surfaceInMetersSquared, String shortName) {
        this.fullName = fullName;
        this.surfaceInMetersSquared = surfaceInMetersSquared;
        this.shortName = shortName;
    }

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
