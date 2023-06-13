package mas.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Framework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String name;
    String company;
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    @ToString.Exclude
    List<Initiative> initiatives;

    public Framework(String name, String company){
        this.company = company;
        this.name = name;
        this.initiatives = new ArrayList<>();
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



}
