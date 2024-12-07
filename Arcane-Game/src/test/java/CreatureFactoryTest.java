import characters.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreatureFactoryTest {
    @Test
    public void testInit() {
        CreatureFactory fact = new CreatureFactory();
        assertEquals(CreatureFactory.class, fact.getClass());
    }
    @Test
    public void testCreateDemon()
    {
        Creature karl = CreatureFactory.createCreature("demon");
        assertNotNull(karl);
        assertInstanceOf(Demon.class, karl);
        Creature garibaldi = CreatureFactory.createCreature("demon", "garibaldi");
        assertNotNull(garibaldi);
        assertInstanceOf(Demon.class, garibaldi);
        assertEquals("garibaldi", garibaldi.getName());
    }
    @Test
    public void testCreateCreature()
    {
        Creature johan = CreatureFactory.createCreature("creature");
        assertNotNull(johan);
        assertInstanceOf(Creature.class, johan);
        Creature steve = CreatureFactory.createCreature("creature", "steve");
        assertNotNull(steve);
        assertInstanceOf(Creature.class, steve);
        assertEquals("steve", steve.getName());
    }
}
