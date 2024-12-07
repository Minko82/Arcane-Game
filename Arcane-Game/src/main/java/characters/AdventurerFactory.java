package characters;

public class AdventurerFactory {
    public static Adventurer createAdventurer(String advenType)
    {
        if(advenType.compareToIgnoreCase("adventurer") == 0)
        {
            return new Adventurer();
        }
        else if(advenType.compareToIgnoreCase("coward") == 0)
        {
            return new Coward();
        }
        else if(advenType.compareToIgnoreCase("glutton") == 0)
        {
            return new Glutton();
        }
        else if(advenType.compareToIgnoreCase("knight") == 0)
        {
            return new Knight();
        }
        else
        {
            throw new IllegalArgumentException("Invalid Adventurer Type");
        }
    }
    public static Adventurer createAdventurer(String advenType, String name)
    {
        if(advenType.compareToIgnoreCase("adventurer") == 0)
        {
            return new Adventurer(name);
        }
        else if(advenType.compareToIgnoreCase("coward") == 0)
        {
            return new Coward(name);
        }
        else if(advenType.compareToIgnoreCase("glutton") == 0)
        {
            return new Glutton(name);
        }
        else if(advenType.compareToIgnoreCase("knight") == 0)
        {
            return new Knight(name);
        }
        else
        {
            throw new IllegalArgumentException("Invalid Adventurer Type");
        }
    }
}
