package actions;
import java.util.*;
// import java.util.logging.Logger;
import characters.Characters;
import maze.*;
// import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class Actions {
    private static final Logger logger = LoggerFactory.getLogger("csci.ooad.arcane.Arcane");
    public static Actions createActions()
    {
        Actions actions = new Actions();
        return actions;
    }
    // returns a negative number if the monster wins and a positive number if the player wins
    public double fight(Characters player, Characters monster) { // This class displays cohesion because all the functions
                                                              // relate more to the actions of the game than they relate to any
                                                              // of the pieces in the game.
        double totalDamage = 0;
        double playerAttack = player.rollDie();
        double monsterAttack = monster.rollDie();

        monster.setHealth(monster.getHealth() - 0.5);
        player.setHealth(player.getHealth() - 0.5);

        if (playerAttack == monsterAttack) {
            return 0.0;
        } else if (playerAttack > monsterAttack) {
            totalDamage = playerAttack - monsterAttack;
            monster.setHealth(monster.getHealth() - totalDamage);
            return totalDamage;
        } else if (playerAttack < monsterAttack) {
            totalDamage = monsterAttack - playerAttack;
            player.setHealth(player.getHealth() - totalDamage);
            return -totalDamage;
        } else {
            throw new IllegalArgumentException("Unchecked exception has occured during fight.");
        }
    }

    public void runGame(Maze maze) { // only runs the first player
        int round = 1;
        double result = -1;

        Maze.Room[] rooms = maze.getRooms();
        int roomCount = maze.getNumOfRooms();

        Random rand = new Random(System.currentTimeMillis());
        logger.info("Starting play... \n");

        while ( (maze.getTotalPlayerHealth() > 0 && maze.getTotalMonsterHealth() > 0) ) {
            logger.info("ARCANE MAZE: turn " + round + "\n");
            

            for (int i = 0; i < roomCount; i++) {
                int[] adj = rooms[i].getAdj();

                List<Characters> demons = new ArrayList<Characters>();
                List<Characters> regMonsters = new ArrayList<Characters>();
                List<Characters> knights = new ArrayList<Characters>();
                List<Characters> cowards = new ArrayList<Characters>();
                List<Characters> gluttons = new ArrayList<Characters>();
                List<Characters> regAdvents = new ArrayList<Characters>();
                List<Item> items = new ArrayList<Item>();
                for (int j = 0; j < rooms[i].getMonsterSize(); j++) {
                    Characters workingMonster = rooms[i].getRoomMonster(j);
                    if (workingMonster.getHealth() > 0) {
                        if (workingMonster.getType().equals("Demon")){
                            demons.add(workingMonster);
                        }
                        else if (workingMonster.getType().equals("RegCreature")){
                            regMonsters.add(workingMonster);
                        }
                    }
                }
                for (int j = 0; j < rooms[i].getAdventurerSize(); j++) {
                    Characters workingAdvent = rooms[i].getRoomAdventurer(j);
                    if (workingAdvent.getHealth() > 0) {
                        if (workingAdvent.getType().equals("Knight")){
                            knights.add(workingAdvent);
                        }
                        else if (workingAdvent.getType().equals("Coward")){
                            cowards.add(workingAdvent);
                        }
                        else if (workingAdvent.getType().equals("Glutton")){
                            gluttons.add(workingAdvent);
                        }
                        else if (workingAdvent.getType().equals("RegAdventurer")){
                            regAdvents.add(workingAdvent);
                        }
                    }
                }
                for (int j = 0; j < rooms[i].getItemSize(); j++) {
                    items.add(rooms[i].getRoomItem(j));
                }
                gameStateDisplay(rooms[i], round, demons, regMonsters, knights, cowards, gluttons, regAdvents, items);
                if (rooms[i].getMonsterSize() > 0 && rooms[i].getAdventurerSize() > 0) { 
                    // demons fight all if there are demons then room over
                    if (demons.size() > 0) {
                        int demonSize = demons.size();
                        for (int j = 0; j < demonSize; j++) {
                            for (int k = 0; k < knights.size(); k++) {
                                result = fight(knights.get(k), demons.get(0));
                                fightDisplay(knights.get(k), demons.get(0), result);
                            }
                            for (int k = 0; k < cowards.size(); k++) {
                                result = fight(cowards.get(k), demons.get(0));
                                fightDisplay(cowards.get(k), demons.get(0), result);
                            }
                            for (int k = 0; k < gluttons.size(); k++) {
                                result = fight(gluttons.get(k), demons.get(0));
                                fightDisplay(gluttons.get(k), demons.get(0), result);
                            }
                            for (int k = 0; k < regAdvents.size(); k++) {
                                result = fight(regAdvents.get(k), demons.get(0));
                                fightDisplay(regAdvents.get(k), demons.get(0), result);
                            }
                            demons.remove(0);
                        }
                        if (knights.size() > 0 && regMonsters.size() > 0) {
                            for (int j = 0; j < knights.size(); j++) {
                                for (int k = 0; k < regMonsters.size(); k++) {
                                    result = fight(knights.get(j), regMonsters.get(k));
                                    fightDisplay(knights.get(j), regMonsters.get(k), result);
                                }
                            }
                        }
                        regMonsters.clear();
                        knights.clear();
                        cowards.clear();
                        gluttons.clear();
                        regAdvents.clear();
                    }

                    // gluttons will eat food if there is any
                    int bound = gluttons.size();
                    if (gluttons.size() > 0 && rooms[i].getItemSize() > 0) {
                        if (rooms[i].getItemSize() < bound) {
                            bound = rooms[i].getItemSize();
                        }
                        for (int j = 0; j < bound; j++) {
                            eatDisplay(gluttons.get(0), rooms[i].getRoomItem(0));
                            maze.consumeItem(rooms[i].getRoomItem(0).getName(), rooms[i]);
                            gluttons.get(0).setHealth(gluttons.get(0).getHealth() + 1);
                            gluttons.remove(0);
                        }
                    }

                    // cowards will run away
                    if (cowards.size() > 0) {
                        for (int j = 0; j < cowards.size(); j++) {
                            int randHold = rand.nextInt(adj.length);
                            moveDisplay(cowards.get(j).getLocation(), adj[randHold], cowards.get(j));
                            maze.movePlayer(cowards.get(j).getLocation(), adj[randHold], cowards.get(j));
                            cowards.get(j).setTakenTurn(true);
                        }
                        cowards.clear();
                    }

                    // knights then fight 
                    if (knights.size() > 0 && regMonsters.size() > 0) {
                        for (int j = 0; j < knights.size(); j++) {
                            for (int k = 0; k < regMonsters.size(); k++) {
                                result = fight(knights.get(0), regMonsters.get(k));
                                fightDisplay(knights.get(0), regMonsters.get(k), result);
                            }
                            knights.remove(0);
                        }
                        regMonsters.clear();
                    }

                    // if there are any knights left and food left they eat food
                    if (knights.size() > 0 && rooms[i].getItemSize() > 0) {
                        bound = knights.size();
                        if (rooms[i].getItemSize() < bound) {
                            bound = rooms[i].getItemSize();
                        }
                        for (int j = 0; j < bound; j++) {
                            eatDisplay(knights.get(0), rooms[i].getRoomItem(0));
                            maze.consumeItem(rooms[i].getRoomItem(0).getName(), rooms[i]);
                            knights.get(0).setHealth(knights.get(0).getHealth() + 1);
                            knights.remove(0);
                        } 
                    }

                    // regular adventurers fight
                    if (regAdvents.size() > 0 && regMonsters.size() > 0) {
                        for (int j = 0; j < regAdvents.size(); j++) {
                            int randHold = rand.nextInt(regMonsters.size());
                            result = fight(regAdvents.get(0), regMonsters.get(randHold));
                            fightDisplay(regAdvents.get(0), regMonsters.get(randHold), result);
                            regAdvents.remove(0); regMonsters.remove(randHold);
                        }
                    }

                    // Gluttons then fight if there are any left
                    if (gluttons.size() > 0 && regMonsters.size() > 0) {
                        for (int j = 0; j < gluttons.size(); j++) {
                            int randHold = rand.nextInt(regMonsters.size());
                            result = fight(gluttons.get(0), regMonsters.get(randHold));
                            fightDisplay(gluttons.get(0), regMonsters.get(randHold), result);
                            gluttons.remove(0); regMonsters.remove(randHold);
                        }
                    }
                }
                else if (rooms[i].getMonsterSize() <= 0 && rooms[i].getAdventurerSize() > 0) {
                    // glutton eats
                    int bound = gluttons.size();
                    if (gluttons.size() > 0 && rooms[i].getItemSize() > 0) {
                        if (rooms[i].getItemSize() < bound) {
                            bound = rooms[i].getItemSize();
                        }
                        for (int j = 0; j < bound; j++) {
                            eatDisplay(gluttons.get(0), rooms[i].getRoomItem(0));
                            maze.consumeItem(rooms[i].getRoomItem(0).getName(), rooms[i]);
                            gluttons.get(0).setHealth(gluttons.get(0).getHealth() + 1);
                            gluttons.remove(0);
                        }
                    }
                    
                    // knight eats
                    bound = knights.size();
                    if (knights.size() > 0 && rooms[i].getItemSize() > 0) {
                        if (rooms[i].getItemSize() < bound) {
                            bound = rooms[i].getItemSize();
                        }
                        for (int j = 0; j < bound; j++) {
                            eatDisplay(knights.get(0), rooms[i].getRoomItem(0));
                            maze.consumeItem(rooms[i].getRoomItem(0).getName(), rooms[i]);
                            knights.get(0).setHealth(knights.get(0).getHealth() + 1);
                            knights.remove(0);
                        }
                    }

                    // coward eats
                    bound = cowards.size();
                    if (cowards.size() > 0 && rooms[i].getItemSize() > 0) {
                        if (rooms[i].getItemSize() < bound) {
                            bound = rooms[i].getItemSize();
                        }
                        for (int j = 0; j < bound; j++) {
                            eatDisplay(cowards.get(0), rooms[i].getRoomItem(0));
                            maze.consumeItem(rooms[i].getRoomItem(0).getName(), rooms[i]);
                            cowards.get(0).setHealth(cowards.get(0).getHealth() + 1);
                            cowards.remove(0);
                        }
                    }

                    // regAdventurers eat
                    bound = regAdvents.size();
                    if (regAdvents.size() > 0 && rooms[i].getItemSize() > 0) {
                        if (rooms[i].getItemSize() < bound) {
                            bound = rooms[i].getItemSize();
                        }
                        for (int j = 0; j < bound; j++) {
                            eatDisplay(regAdvents.get(0), rooms[i].getRoomItem(0));
                            maze.consumeItem(rooms[i].getRoomItem(0).getName(), rooms[i]);
                            regAdvents.get(0).setHealth(regAdvents.get(0).getHealth() + 1);
                            regAdvents.remove(0);
                        }
                    }

                    // coward moves
                    if (cowards.size() > 0) {
                        for (int j = 0; j < cowards.size(); j++) {
                            int randHold = rand.nextInt(adj.length);
                            moveDisplay(cowards.get(j).getLocation(), adj[randHold], cowards.get(j));
                            maze.movePlayer(cowards.get(j).getLocation(), adj[randHold], cowards.get(j));
                            cowards.get(j).setTakenTurn(true);
                        }
                        cowards.clear();
                    }

                    // knight moves
                    if (knights.size() > 0) {
                        for (int j = 0; j < knights.size(); j++) {
                            int randHold = rand.nextInt(adj.length);
                            moveDisplay(knights.get(j).getLocation(), adj[randHold], knights.get(j));
                            maze.movePlayer(knights.get(j).getLocation(), adj[randHold], knights.get(j));
                            knights.get(j).setTakenTurn(true);
                        }
                        knights.clear();
                    }

                    // glutton moves
                    if (gluttons.size() > 0) {
                        for (int j = 0; j < gluttons.size(); j++) {
                            int randHold = rand.nextInt(adj.length);
                            moveDisplay(gluttons.get(j).getLocation(), adj[randHold], gluttons.get(j));
                            maze.movePlayer(gluttons.get(j).getLocation(), adj[randHold], gluttons.get(j));
                            gluttons.get(j).setTakenTurn(true);
                        }
                        gluttons.clear();
                    }

                    // regAdventurer moves
                    if (regAdvents.size() > 0) {
                        for (int j = 0; j < regAdvents.size(); j++) {
                            int randHold = rand.nextInt(adj.length);
                            moveDisplay(regAdvents.get(j).getLocation(), adj[randHold], regAdvents.get(j));
                            maze.movePlayer(regAdvents.get(j).getLocation(), adj[randHold], regAdvents.get(j));
                            regAdvents.get(j).setTakenTurn(true);
                        }
                        regAdvents.clear();
                    }
                }
            }
            round += 1;
        }

        if (maze.getTotalPlayerHealth() <= 0) {
            logger.info("The Creatures won!\n");
        }
        else if (maze.getTotalMonsterHealth() <= 0 ) {
            logger.info("The Adventurers won!\n");
        }
    }
    public void gameStateDisplay(Maze.Room room, int round, List<Characters> demons, List<Characters> regMonsters, List<Characters> knights, List<Characters> cowards, List<Characters> gluttons, List<Characters> regAdvents, List<Item> items) {
        logger.info("   Room " + room.getId() + ":\n");

        logger.info("     Adventurers: ");
        for (int i = 0; i < knights.size(); i++) {
            logger.info(knights.get(i).getName() + "(knight) ");
        }
        for (int i = 0; i < cowards.size(); i++) {
            logger.info(cowards.get(i).getName() + "(coward) ");
        }
        for (int i = 0; i < gluttons.size(); i++) {
            logger.info(gluttons.get(i).getName() + "(glutton) ");
        }
        for (int i = 0; i < regAdvents.size(); i++) {
            logger.info(regAdvents.get(i).getName() + "(adventurer) ");
        }
        logger.info("\n");

        logger.info("     Creatures: ");
        for (int i = 0; i < demons.size(); i++) {
            logger.info(demons.get(i).getName() + "(demon) ");
        }
        for (int i = 0; i < regMonsters.size(); i++) {
            logger.info(regMonsters.get(i).getName() + "(monster) ");
        }
        logger.info("\n");

        logger.info("     Items: ");
        for (int i = 0; i < items.size(); i++) {
            logger.info(items.get(i).getName() + "(item) ");
        }
        logger.info("\n");

    }
    public void fightDisplay(Characters advent, Characters monster, double result) {
        logger.info("Adventurer " + advent.getName() + " fought monster " + monster.getName() + "\n");
        if (result > 0) {
            logger.info("     Adventurer " + advent.getName() + " dealt " + result + " damage to the monster! \n");
        } if (result < 0) {
            logger.info("     Monster " + monster.getName() + " dealt " + Math.abs(result) + " damage to the adventurer! \n");
        } else {
            logger.info("     The adventurer and monster tied! \n");
        }
    }
    public void eatDisplay(Characters advent, Item item) {
        logger.info("     Adventurer " + advent.getName() + " ate food! \n ");
    }
    public void moveDisplay(int start, int end, Characters advent) {
        logger.info("     Adventurer " + advent.getName() + " moved from room " + start + " to room " + end + "\n");
    }
}
