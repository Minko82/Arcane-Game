package characters;

public class Adventurer extends Characters { // This is an example Inheritance, as it is inheriting the defined methods from the Characters parent class

    public Adventurer(String name) {
        super();
        setHealth(5);
        setName(name);
        setType("RegAdventurer");
    }
    public Adventurer()
    {
        super();
    }
}