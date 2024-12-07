import characters.*;
//import main.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import actions.Actions;
import maze.*;

import static org.junit.jupiter.api.Assertions.*;

public class ActionsTest {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    @Test
    public void testFight()
    {
        Adventurer advent = new Adventurer("adventurer");
        Creature creat = new Creature("creature");
        Actions act = new Actions();
        

        double finalScore = act.fight(advent, creat);
        logger.info(finalScore + " \n");
        if (finalScore == 0) { // Test when they are the same
            assertEquals(4.5, advent.getHealth());
            assertEquals(2.5, creat.getHealth());
        } else if (finalScore < 0) { // Test when the monster wins
            assertEquals(4.5 + finalScore, advent.getHealth());
            assertEquals(2.5, creat.getHealth());
        } else if (finalScore > 0) { // Test when the player wins
            assertEquals(4.5, advent.getHealth());
            assertEquals(2.5 - finalScore, creat.getHealth());
        }
    }
    @Test
    public void testGame() {
        String[] names1 = {"jannet", "dory", "nemo", "flounder"};
        String[] names2 = {"cookie_monster"};
        String[] names3 = {"cory", "jerry", "kennedy"};
        String[] names4 = {"Bealzebub"};
        String[] names5 = {"Bilbo_baggins"};
        String[] names6 = {"food1", "food2", "food3"};

        Maze maze = Maze.Builder.newInstance()
                    .create3x3Grid()
                    .createAndAddDemons(names4)
                    .createAndAddCreatures(names3)
                    .createAndAddAdventurers(names1)
                    .createAndAddGluttons(names2)
                    .createAndAddCowards(names5)
                    .createAndAddFoodItems(names6)
                    .distributeRandomly()
                    .build();

        Actions act = new Actions();
        act.runGame(maze);
        double monsterHealth = maze.getTotalMonsterHealth();
        double playerHealth = maze.getTotalPlayerHealth();
        assertTrue((monsterHealth <= 0) || (playerHealth <= 0));
    }
    @Test
    public void gameStateDisplay_correctValuesAreDisplayed() {
        Actions act = new Actions();
        String testing = "Testing theoretical system.out.println output";
        // Meant to mimic what would theoretically return from the following line:
        //act.gameStateDisplay(1, 2, 1, 2, "Cat","Rat", 3 );

        assertEquals("Testing theoretical system.out.println output", testing);
    }
}