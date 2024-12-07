package maze;

public class ItemFactory {
    public static Item createItem(String itemType, String name)
    {
        if(itemType.compareToIgnoreCase("corn") == 0)
        {
            return new Item(name);
        }
        else
        {
            throw new IllegalArgumentException("Invalid Item Type");
        }
    }
}
