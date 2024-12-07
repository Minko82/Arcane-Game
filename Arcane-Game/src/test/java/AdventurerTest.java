import characters.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdventurerTest
{
    @Test
    public void testAdventurerCreation()
    {
        Adventurer advent = new Adventurer("John");
        assertEquals(5, advent.getHealth());
        assertEquals("John", advent.getName());
    }
    @Test
    public void testAdventurerHealthSetter()
    {
        Adventurer advent = new Adventurer("timmy");
        advent.setHealth(7);
        assertEquals(7, advent.getHealth());
    }
    @Test
    public void testAdventurerNameSetter()
    {
        Adventurer advent = new Adventurer("jerry");
        advent.setName("Igor");
        assertEquals("Igor", advent.getName());
    }
}
