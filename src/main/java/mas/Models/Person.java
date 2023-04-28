package mas.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.ListIndexJavaType;
import org.hibernate.type.descriptor.java.StringJavaType;

import java.time.LocalDate;
import java.util.EnumSet;


enum PersonTraits {Person, Employee, Client}

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personGen")
    @SequenceGenerator(name = "personGen", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;
    String firstName;
    String lastName;
    LocalDate birthDate;

    /**
     * Przykład overlappingu
     * */
    @Enumerated()
    @ElementCollection(targetClass = Enum.class)
    @ListIndexJavaType(StringJavaType.class)
    protected EnumSet<PersonTraits> personTraits = EnumSet.of(PersonTraits.Person);

    public EnumSet<PersonTraits> getPersonTraits() {
        return personTraits;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Przykład dziedziczenia klasa abstrakcyjna oraz polimorficzne
     * wołanie metod
     */
    public Person(String firstName, String lastName, LocalDate birthDate) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public abstract String getData();
}
