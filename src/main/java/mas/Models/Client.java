package mas.Models;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Client extends Person {
    int netWorth;

    public Client (String firstName, String lastName, LocalDate birthDate, int netWorth){
        super(firstName, lastName, birthDate);
        personTraits.add(PersonTraits.Client);
        this.netWorth = netWorth;
    }

    public Client() {

    }

    public int getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(int netWorth) {
        this.netWorth = netWorth;
    }

    @Override
    public String getData() {
        return firstName+' '+lastName+' '+getNetWorth();
    }
}
