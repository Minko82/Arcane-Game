import characters.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatureTest
{
    @Test
    public void testCreatureCreation()
    {
        Creature creature = new Creature("Blorg");
        assertEquals(3, creature.getHealth());
        assertEquals("Blorg", creature.getName());
    }
    @Test
    public void testCreatureHealthSetter()
    {
        Creature creature = new Creature("skrank");
        creature.setHealth(8);
        assertEquals(8, creature.getHealth());
    }
    @Test
    public void testCreatureNameSetter()
    {
        Creature creature = new Creature("Blorg");
        creature.setName("Esteemed Sir John");
        assertEquals("Esteemed Sir John", creature.getName());
    }
}
