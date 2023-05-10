package mas.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ListIndexJavaType;
import org.hibernate.type.descriptor.java.StringJavaType;

import java.time.LocalDate;
import java.util.EnumSet;


enum PersonTraits {Person, Employee, Client}

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @NonNull
    String firstName;
    @NonNull
    String lastName;
    @NonNull
    LocalDate birthDate;
    /**
     * Przykład dziedziczenia klasa abstrakcyjna oraz polimorficzne
     * wołanie metod
     */


    public abstract String getData();
}
