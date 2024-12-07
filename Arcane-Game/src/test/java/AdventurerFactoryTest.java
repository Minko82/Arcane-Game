import characters.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdventurerFactoryTest {
    @Test
    public void testCreateAdventurer()
    {
        Adventurer karlach = AdventurerFactory.createAdventurer("adventurer");
        assertNotNull(karlach);
        assertInstanceOf(Adventurer.class, karlach);
        Adventurer jaheira = AdventurerFactory.createAdventurer("adventurer", "jaheira");
        assertNotNull(jaheira);
        assertInstanceOf(Adventurer.class, jaheira);
        assertEquals("jaheira", jaheira.getName());
    }
    @Test
    public void testCreateKnight()
    {
        Adventurer halsin = AdventurerFactory.createAdventurer("knight");
        assertNotNull(halsin);
        assertInstanceOf(Knight.class, halsin);
        Adventurer ketheric = AdventurerFactory.createAdventurer("knight", "ketheric");
        assertNotNull(ketheric);
        assertInstanceOf(Knight.class, ketheric);
        assertEquals("ketheric", ketheric.getName());
    }
    @Test
    public void testCreateCoward()
    {
        Adventurer astarion = AdventurerFactory.createAdventurer("coward");
        assertNotNull(astarion);
        assertInstanceOf(Coward.class, astarion);
        Adventurer gale = AdventurerFactory.createAdventurer("knight", "gale");
        assertNotNull(gale);
        assertInstanceOf(Knight.class, gale);
        assertEquals("gale", gale.getName());
    }
    @Test
    public void testCreateGlutton()
    {
        Adventurer shadowheart = AdventurerFactory.createAdventurer("glutton");
        assertNotNull(shadowheart);
        assertInstanceOf(Glutton.class, shadowheart);
        Adventurer laezel = AdventurerFactory.createAdventurer("glutton", "laezel");
        assertNotNull(laezel);
        assertInstanceOf(Glutton.class, laezel);
        assertEquals("laezel", laezel.getName());
    }
}
