package entity;

import java.util.List;

public class City extends PrototypeEntity {
    private List<Machine> machines;

    public City(String name, List<Machine> machines) {
        super(name);
        this.machines = machines;
    }
}
