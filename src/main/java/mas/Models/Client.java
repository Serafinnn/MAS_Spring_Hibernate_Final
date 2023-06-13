package mas.Models;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Client extends Person {
    @NonNull
    int netWorth;

    public Client ( String firstName, String lastName, LocalDate birthDate, int netWorth){
        super( firstName, lastName, birthDate);
        this.netWorth = netWorth;
    }

    @Override
    public String getData() {
        return firstName+' '+lastName+' '+getNetWorth();
    }
}
