package maze;
import maze.*;

public class Item
{
    private String name;
    private int location;
    public Item(String name)
    {
        this.name = name;
    }


    public String getName() {return name;}
    public void setLocation(int index) {location = index;}
    public int getLocation() {return location;}
}