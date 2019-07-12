package newIO;

import java.io.Serializable;

public class RandomClass implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RandomClass(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
