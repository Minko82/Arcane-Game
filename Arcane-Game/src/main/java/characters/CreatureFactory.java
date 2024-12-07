package characters;

public class CreatureFactory {
    public static Creature createCreature(String creatureType)
    {
        if(creatureType.compareToIgnoreCase("demon") == 0)
        {
            return new Demon();
        }
        else if(creatureType.compareToIgnoreCase("creature") == 0)
        {
            return new Creature();
        }
        else
        {
            throw new IllegalArgumentException("Invalid Creature Type");
        }
    }
    public static Creature createCreature(String creatureType, String name)
    {
        if(creatureType.compareToIgnoreCase("demon") == 0)
        {
            return new Demon(name);
        }
        else if(creatureType.compareToIgnoreCase("creature") == 0)
        {
            return new Creature(name);
        }
        else
        {
            throw new IllegalArgumentException("Invalid Creature Type");
        }
    }
}
