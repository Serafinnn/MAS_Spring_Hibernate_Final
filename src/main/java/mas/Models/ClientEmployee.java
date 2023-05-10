package mas.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.time.LocalDate;

@Entity
public class ClientEmployee extends Employee implements IClient {
    @Transient
    Client client;

    /**
     * Przykład wielodziedziczenia
     */
    public ClientEmployee(int discount, String firstName, String lastName, LocalDate birthDate, double salary, LocalDate empDate, Department dept, int netWorth) throws Exception {
        super(firstName, lastName, birthDate, salary, empDate, dept);
        this.client = new Client(null, null, null, netWorth);
    }

    public ClientEmployee() {

    }

    @Override
    public int getNetWorth() {
        return client.getNetWorth();
    }

    @Override
    public void setNetWorth(int net) {
        this.client.setNetWorth(net);
    }
}
