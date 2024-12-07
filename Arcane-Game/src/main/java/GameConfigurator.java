import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import maze.*;
import characters.*;
import actions.Actions;

import org.apache.commons.cli.*;

public class GameConfigurator {

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption("r", "numberOfRooms", true, "Number of rooms");
        options.addOption("a", "numberOfAdventurers", true, "Number of adventurers");
        options.addOption("c", "numberOfCreatures", true, "Number of creatures");
        options.addOption("f", "numberOfFoodItems", true, "Number of food items");

        try {
            CommandLine cmd = parser.parse(options, args);

            int numberOfRooms = Integer.parseInt(cmd.getOptionValue("r"));
            int numberOfAdventurers = Integer.parseInt(cmd.getOptionValue("a"));
            int numberOfCreatures = Integer.parseInt(cmd.getOptionValue("c"));
            int numberOfFoodItems = Integer.parseInt(cmd.getOptionValue("f"));

            String[] advNames = {"steve", "jerry"};
            String[] creNames = {"glorb", "gluub"};
            String[] foodItems = {"corn", "popcorn"};
            Maze maze = Maze.Builder.newInstance()
                    .create3x3Grid()
                    .createAndAddAdventurers(advNames)
                    .createAndAddCreatures(creNames)
                    .createAndAddFoodItems(foodItems)
                    .distributeRandomly()
                    .build();

            Actions action = new Actions();
            action.runGame(maze);
        } catch (ParseException | NumberFormatException e) {
            System.err.println("Error parsing command-line arguments: " + e.getMessage());
            System.exit(1);
        }
    }
}
