package characters;
import java.util.concurrent.ThreadLocalRandom;

public class Characters {
    public Characters() {
        health = 1;
        name = "characterConstructor";
    }
    protected double health;
    protected String name;
    protected String type;
    protected int location;
    protected int attack;
    protected int index; // where it sits in the room buffers
    protected boolean takenTurn;

    public void setHealth(double newHealth) {
        this.health = newHealth;
    }

    public double getHealth() {
        return this.health;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setTakenTurn(boolean newTaken) {
        takenTurn = newTaken;
    }

    public boolean getTakenTurn() {
        return takenTurn;
    }

    public String getName() {
        return this.name;
    }
    public void setType(String newType){
        this.type = newType;
    }
    public String getType(){
        return this.type;
    }

    public void setLocation(int newLocation) {
        this.location = newLocation;
    }

    public int getLocation() {
        return this.location;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int ind) {
        index = ind;
    }

    public int rollDie() {
        this.attack = ThreadLocalRandom.current().nextInt(1, 7);
        return this.attack;
    }



    public boolean isAlive(){
        return health > 0;
    }
}