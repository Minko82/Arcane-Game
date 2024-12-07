package characters;

public class Creature extends Characters { // This is an example Inheritance, as it is inheriting the defined methods from the Characters parent class
    public Creature(String name) {
        super();
        setHealth(3);
        setName(name);
        setType("RegCreature");
    }
    public Creature()
    {
        super();
    }
        // Characters creature = new Creature(); This will be polymorphism when we get to it
}