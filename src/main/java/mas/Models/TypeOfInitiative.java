package mas.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TypeOfInitiative implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany
    @ToString.Exclude
    private List<Initiative> initiatives = new ArrayList<>();

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

}
