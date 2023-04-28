package mas.Models;

import jakarta.persistence.Entity;

@Entity
public class PaidFramework extends Framework{
    private int monthlyPrice;

    public PaidFramework(String name, String company, int monthlyPrice) {
        super(name, company);
        this.monthlyPrice = monthlyPrice;
    }

    public PaidFramework() {
        super();
    }

    public int getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(int monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

}
