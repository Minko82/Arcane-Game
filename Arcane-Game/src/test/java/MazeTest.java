import maze.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import characters.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class MazeTest
{
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    @Test
    public void testMazeCreation() {
        Characters teddy = AdventurerFactory.createAdventurer("knight", "teddy");
        Characters bealzebub = CreatureFactory.createCreature("demon", "bealzebub");
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .addAdventurerToRoom(2, teddy)
                    .addMonsterToRoom(1, bealzebub)
                    .build();
        assertEquals(2, maze.getPlayerByName("teddy").getLocation());
        assertEquals(1, maze.getMonsterByName("bealzebub").getLocation());
    }
    @Test
    public void testMazeCreation3x3() {
        Characters teddy = AdventurerFactory.createAdventurer("knight", "teddy");
        Characters bealzebub = CreatureFactory.createCreature("demon", "bealzebub");
        Maze maze = Maze.Builder.newInstance()
                    .create3x3Grid()
                    .addAdventurerToRoom(9, teddy)
                    .addMonsterToRoom(7, bealzebub)
                    .build();
    assertEquals(9, maze.getPlayerByName("teddy").getLocation());
    assertEquals(7, maze.getMonsterByName("bealzebub").getLocation());
    }
    @Test
    public void testCreateFullyConnectedRooms() {
        Maze maze = Maze.Builder.newInstance()
                    .createFullyConnectedRooms(3)
                    .build();
        int[] adj1 = {2, 3, 4, 5, 6, 7, 8, 9};
        int[] adj2 = {1, 3, 4, 5, 6, 7, 8, 9};
        int[] adj3 = {1, 2, 4, 5, 6, 7, 8, 9};
        int[] adj4 = {1, 2, 3, 5, 6, 7, 8, 9};
        int[] adj5 = {1, 2, 3, 4, 6, 7, 8, 9};
        int[] adj6 = {1, 2, 3, 4, 5, 7, 8, 9};
        int[] adj7 = {1, 2, 3, 4, 5, 6, 8, 9};
        int[] adj8 = {1, 2, 3, 4, 5, 6, 7, 9};
        int[] adj9 = {1, 2, 3, 4, 5, 6, 7, 8};

        Maze.Room[] rooms = maze.getRooms();
        assertTrue(Arrays.equals(adj1, rooms[0].getAdj()));
        assertTrue(Arrays.equals(adj2, rooms[1].getAdj()));
        assertTrue(Arrays.equals(adj3, rooms[2].getAdj()));
        assertTrue(Arrays.equals(adj4, rooms[3].getAdj()));
        assertTrue(Arrays.equals(adj5, rooms[4].getAdj()));
        assertTrue(Arrays.equals(adj6, rooms[5].getAdj()));
        assertTrue(Arrays.equals(adj7, rooms[6].getAdj()));
        assertTrue(Arrays.equals(adj8, rooms[7].getAdj()));
        assertTrue(Arrays.equals(adj9, rooms[8].getAdj()));
    }
    @Test
    public void testCreateRoom() {
        Maze maze = Maze.Builder.newInstance()
                    .createRoom(1)
                    .build();
        Maze.Room[] rooms = maze.getRooms();
        assertEquals(1, rooms[0].getId());
    }
    @Test
    public void testCreateConnectedRoom() {
        Maze maze = Maze.Builder.newInstance()
                    .createRoom(1)
                    .build();
        Maze.Room[] rooms = maze.getRooms();
        assertEquals(1, rooms[0].getId());
    }
    @Test
    public void testAddItemsToRoom() {
        Item item = ItemFactory.createItem("corn", "food");
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .addItemsToRoom(1, item)
                    .build();
        
        Maze.Room[] rooms = maze.getRooms();
        assertEquals("food", rooms[0].getRoomItem(0).getName());
    }
    @Test
    public void testGetCreatureAdventurerLocation() {
        Characters teddy = AdventurerFactory.createAdventurer("knight", "teddy");
        Characters bealzebub = CreatureFactory.createCreature("demon", "bealzebub");
        Maze maze = Maze.Builder.newInstance()
                    .create3x3Grid()
                    .addAdventurerToRoom(9, teddy)
                    .addMonsterToRoom(7, bealzebub)
                    .build();

        assertEquals(9, maze.getAdventurerLocation(0));
        assertEquals(7, maze.getCreatureLocation(0));
    }
    @Test
    public void testGetPlayerNum() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddAdventurers(stringHold)
                    .distributeRandomly()
                    .build();
        assertEquals(4, maze.getPlayerNum());
    }
    @Test
    public void testGetMonsterNum() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddCreatures(stringHold)
                    .distributeRandomly()
                    .build();
        assertEquals(4, maze.getMonsterNum());
    }
    @Test
    public void testGetAdventurerName() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddAdventurers(stringHold)
                    .distributeRandomly()
                    .build();
        assertEquals("Jeff", maze.getAdventurerName(0));
        assertEquals("Ron", maze.getAdventurerName(1));
        assertEquals("Hermoine", maze.getAdventurerName(2));
        assertEquals("craig", maze.getAdventurerName(3));
    }
    @Test
    public void testGetCreatureName() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddCreatures(stringHold)
                    .distributeRandomly()
                    .build();
        assertEquals("Jeff", maze.getCreatureName(0));
        assertEquals("Ron", maze.getCreatureName(1));
        assertEquals("Hermoine", maze.getCreatureName(2));
        assertEquals("craig", maze.getCreatureName(3));

    }
    @Test
    public void consumeItem() {
        String[] names = {"food", "food", "food", "food"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddFoodItems(names)
                    .distributeRandomly()
                    .build();
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < 4; i++) {
            int size = rooms[i].getItemSize();
            for (int j = 0; j < size; j++) {
                maze.consumeItem("food", rooms[i]);
            }
        }
        assertEquals(0, maze.getNumberOfItemsInAllRooms());
    }
    @Test
    public void initItemFactory() {
        ItemFactory itemFactory = new ItemFactory();
        assertEquals(ItemFactory.class, itemFactory.getClass());
    }
    @Test
    public void initItemFactoryFail() {
        ItemFactory itemFactory = new ItemFactory();
        assertEquals(ItemFactory.class, itemFactory.getClass());
        assertThrows(IllegalArgumentException.class, () ->
            ItemFactory.createItem("", ""));

    }
    @Test
    public void testAddAdventurers() {
        Characters teddy = AdventurerFactory.createAdventurer("knight", "teddy");
        Characters franklin = AdventurerFactory.createAdventurer("coward", "franklin");
        Characters amy = AdventurerFactory.createAdventurer("knight", "amy");
        Characters[] charHold = {teddy, franklin, amy};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .addAdventurers(charHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            count += rooms[i].getAdventurerSize();
        }
        assertEquals(3, count);
    }
    @Test
    public void testAddCreatures() {
        Characters teddy = CreatureFactory.createCreature("demon", "teddy");
        Characters franklin = CreatureFactory.createCreature("demon", "franklin");
        Characters amy = CreatureFactory.createCreature("demon", "amy");
        Characters[] charHold = {teddy, franklin, amy};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .addCreatures(charHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            count += rooms[i].getMonsterSize();
        }
        assertEquals(3, count);
    }
    @Test
    public void removeRoomMonster() {
        Characters teddy = CreatureFactory.createCreature("demon", "teddy");
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .addMonsterToRoom(1, teddy)
                    .distributeRandomly()
                    .build();
        Maze.Room[] rooms = maze.getRooms();
        Characters removed = rooms[0].removeRoomMonster("teddy");
        assertEquals("teddy", removed.getName());
    }
    @Test
    public void testGetItemLocation() {
        Item item = ItemFactory.createItem("corn", "food");
        item.setLocation(1);
        assertEquals(1, item.getLocation());
    }
    @Test
    public void testAddItems() {
        Item corn = ItemFactory.createItem("corn", "food");
        Item moreCorn = ItemFactory.createItem("corn", "moreFood");
        Item[] charHold = {corn, moreCorn};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .addItems(charHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            count += rooms[i].getItemSize();
        }
        assertEquals(2, count);
    }
    @Test
    public void testCreateAndAddAdventurers() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddAdventurers(stringHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            if (rooms[i].getAdventurerSize() > 0) {
                assertEquals("RegAdventurer", rooms[i].getRoomAdventurer(0).getType());
            }
            count += rooms[i].getAdventurerSize();
        }
        assertEquals(4, count);
    }
    @Test
    public void testCreateAndAddCowards() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddCowards(stringHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            if (rooms[i].getAdventurerSize() > 0) {
                assertEquals("Coward", rooms[i].getRoomAdventurer(0).getType());
            }
            count += rooms[i].getAdventurerSize();
        }
        assertEquals(4, count);
    }
    @Test
    public void testCreateAndAddKnights() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddKnights(stringHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            if (rooms[i].getAdventurerSize() > 0) {
                assertEquals("Knight", rooms[i].getRoomAdventurer(0).getType());
            }
            count += rooms[i].getAdventurerSize();
        }
        assertEquals(4, count);
    }
    @Test
    public void testCreateAndAddGluttons() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddGluttons(stringHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            if (rooms[i].getAdventurerSize() > 0) {
                assertEquals("Glutton", rooms[i].getRoomAdventurer(0).getType());
            }
            count += rooms[i].getAdventurerSize();
        }
        assertEquals(4, count);
    }
    @Test
    public void testCreateAndAddCreatures() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddCreatures(stringHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            if (rooms[i].getMonsterSize() > 0) {
                assertEquals("RegCreature", rooms[i].getRoomMonster(0).getType());
            }
            count += rooms[i].getMonsterSize();
        }
        assertEquals(4, count);
    }
    @Test
    public void testCreateAndAddDemons() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddDemons(stringHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            if (rooms[i].getMonsterSize() > 0) {
                assertEquals("Demon", rooms[i].getRoomMonster(0).getType());
            }
            count += rooms[i].getMonsterSize();
        }
        assertEquals(4, count);
    }
    @Test
    public void testCreateAndAddFoodItems() {
        String[] stringHold = {"food", "food", "food", "food"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddFoodItems(stringHold)
                    .distributeRandomly()
                    .build();
        int count = 0;
        Maze.Room[] rooms = maze.getRooms();
        for (int i = 0; i < maze.getNumOfRooms(); i++) {
            if (rooms[i].getItemSize() > 0) {
                assertEquals("food", rooms[i].getRoomItem(0).getName());
            }
            count += rooms[i].getItemSize();
        }
        assertEquals(4, count);
    }
    @Test
    public void testDistributeSequentially() {
        String[] stringHold1 = {"Jeff(coward)", "Ron(coward)", "Hermoine(coward)", "craig(coward)", "david(coward)"};
        String[] stringHold2 = {"Jeff(demon)", "Ron(demon)", "Hermoine(demon)", "craig(demon)", "david(demon)"};
        String[] stringHold3 = {"food1", "food2", "food3", "food4"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddCowards(stringHold1)
                    .createAndAddDemons(stringHold2)
                    .createAndAddFoodItems(stringHold3)
                    .distribuiteSequentially()
                    .build();
        Maze.Room[] rooms = maze.getRooms();
        assertEquals("Jeff(coward)", rooms[0].getRoomAdventurer(0).getName());
        assertEquals("Ron(coward)", rooms[1].getRoomAdventurer(0).getName());
        assertEquals("Hermoine(coward)", rooms[2].getRoomAdventurer(0).getName());
        assertEquals("craig(coward)", rooms[3].getRoomAdventurer(0).getName());
        assertEquals("david(coward)", rooms[0].getRoomAdventurer(1).getName());
        assertEquals("Jeff(demon)", rooms[1].getRoomMonster(0).getName());
        assertEquals("Ron(demon)", rooms[2].getRoomMonster(0).getName());
        assertEquals("Hermoine(demon)", rooms[3].getRoomMonster(0).getName());
        assertEquals("craig(demon)", rooms[0].getRoomMonster(0).getName());
        assertEquals("david(demon)", rooms[1].getRoomMonster(1).getName());
        assertEquals("food1", rooms[2].getRoomItem(0).getName());
        assertEquals("food2", rooms[3].getRoomItem(0).getName());
        assertEquals("food3", rooms[0].getRoomItem(0).getName());
        assertEquals("food4", rooms[1].getRoomItem(0).getName());
        
    }
    @Test
    public void testGetAdj() {
        Maze maze = Maze.Builder.newInstance()
                    .create3x3Grid()
                    .build();
        Maze.Room[] rooms = maze.getRooms();
        int[] adj1 = {2, 4};
        int[] adj2 = {1, 5, 3};
        int[] adj3 = {2, 6};
        assertEquals(adj1[0], rooms[0].getAdj()[0]);
        assertEquals(adj2[0], rooms[1].getAdj()[0]);
        assertEquals(adj3[0], rooms[2].getAdj()[0]);
    }
    @Test
    public void testGetPlayer() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddAdventurers(stringHold)
                    .distributeRandomly()
                    .build();
        Characters advent1 = maze.getPlayer(0);
        Characters advent2 = maze.getPlayer(1);
        assertEquals("Jeff", advent1.getName());
        assertEquals("Ron", advent2.getName());
    }
    @Test
    public void testGetCreature() {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddDemons(stringHold)
                    .distributeRandomly()
                    .build();
        Characters creat1 = maze.getMonster(0);
        Characters creat2 = maze.getMonster(1);
        Characters creat3 = maze.getMonster(2);
        Characters creat4 = maze.getMonster(3);
        assertEquals("Jeff", creat1.getName());
        assertEquals("Ron", creat2.getName());
        assertEquals("Hermoine", creat3.getName());
        assertEquals("craig", creat4.getName());
    }
    
   @Test
   public void testMovePlayer()
   {
        String[] stringHold = {"Jeff", "Ron", "Hermoine", "craig"};
        Maze maze = Maze.Builder.newInstance()
                    .create2x2Grid()
                    .createAndAddAdventurers(stringHold)
                    .distributeRandomly()
                    .build();
       Characters player1 = maze.getPlayer(0);
       Characters player2 = maze.getPlayer(1);

       int loc1Hold = player1.getLocation();
       int loc2Hold = player2.getLocation();

       assertNotEquals(player1.getName(), player2.getName());


        assertEquals(player1.getLocation(), maze.getAdventurerLocation(0));
        assertEquals(player2.getLocation(), maze.getAdventurerLocation(1));

        maze.movePlayer(loc1Hold, (loc1Hold + 1) % maze.getNumOfRooms(), player1);
        maze.movePlayer(loc2Hold, (loc2Hold + 1) % maze.getNumOfRooms(), player2);

        assertEquals((loc1Hold + 1) % maze.getNumOfRooms(), maze.getAdventurerLocation(0));
        assertEquals((loc2Hold + 1) % maze.getNumOfRooms(), maze.getAdventurerLocation(1));
        

        assertEquals(player1.getLocation(), maze.getAdventurerLocation(0));
        assertEquals(player2.getLocation(), maze.getAdventurerLocation(1));

   }
   
}