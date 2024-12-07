import maze.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemFactoryTest {
    @Test
    public void testCreateItem()
    {
        Item corn = ItemFactory.createItem("corn", "Jerry");
        assertNotNull(corn);
        assertEquals("Jerry", corn.getName());
    }
}
