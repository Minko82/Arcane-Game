import actions.*;
import characters.*;
import maze.*;
import actions.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class main {
    public static void main(String[] args) {
        String[] names1 = {"jannet", "dory", "nemo", "flounder"};
        String[] names2 = {"cookie_monster"};
        String[] names3 = {"cory", "jerry", "kennedy"};
        String[] names4 = {"Bealzebub"};
        String[] names5 = {"Bilbo_baggins"};
        String[] names6 = {"food1", "food2", "food3"};
        Maze maze = null;

        if (args.length == 0 || args[0].equals("3")) {
            maze = Maze.Builder.newInstance()
                .create3x3Grid()
                .createAndAddDemons(names4)
                .createAndAddCreatures(names3)
                .createAndAddAdventurers(names1)
                .createAndAddGluttons(names2)
                .createAndAddCowards(names5)
                .createAndAddFoodItems(names6)
                .distributeRandomly()
                .build();
        } else if (args[0].equals("2")) {
            maze = Maze.Builder.newInstance()
                .create2x2Grid()
                .createAndAddDemons(names4)
                .createAndAddCreatures(names3)
                .createAndAddAdventurers(names1)
                .createAndAddGluttons(names2)
                .createAndAddCowards(names5)
                .createAndAddFoodItems(names6)
                .distributeRandomly()
                .build();
        }
        

        Actions act = new Actions();
        act.runGame(maze);
        
    }
}