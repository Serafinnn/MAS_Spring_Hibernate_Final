package mas.Models;

import jakarta.persistence.Entity;

@Entity
public class FreeFramework extends Framework{

    String whyFree;

    public FreeFramework(String name, String company, String whyFree) {
        super(name, company);
        this.whyFree = whyFree;
    }

    public FreeFramework() {
        super();
    }

    public String getWhyFree() {
        return whyFree;
    }

    public void setWhyFree(String whyFree) {
        this.whyFree = whyFree;
    }
}
