package maze;
import characters.*;

import java.util.*;

// import javax.smartcardio.CardException;


public class Maze {
    public class Room {
        // adjacency list refers to the ID number of the room, not the room itself
        private int[] adj;
        private int ID;
        private List<Characters> roomMonsters;
        private List<Characters> roomAdventurers;
        private List<Item> roomItems;

        public Room(int id, int[] adjacent) {
            ID = id;
            adj = adjacent;

            roomMonsters = new ArrayList<Characters>();
            roomAdventurers = new ArrayList<Characters>();
            roomItems = new ArrayList<Item>();
        }


        public int getId() {
            return ID;
        }
        public int[] getAdj() {
            return adj;
        }
        public int getMonsterSize() {
            return roomMonsters.size();
        }
        public int getAdventurerSize() {
            return roomAdventurers.size();
        }
        public int getItemSize() {
            return roomItems.size();
        }

        public Characters getRoomMonster(int index) {
            if (index > roomMonsters.size()) {
                return null;
            }
            else {
                return roomMonsters.get(index);
            }
        }
        public void addRoomMonster(Characters monster) {
            boolean found = false;
            if (monsters == null) {
                monsters = new Characters[1];
                monsters[0] = monster;
            }
            else {
                for (int i = 0; i < monsters.length; i++) {
                    if (monster.getName().equals(monsters[i].getName())) {
                        found = true;
                    }
                }
                if (!found) {
                    Characters[] monHold = Arrays.copyOf(monsters, monsters.length + 1);
                    monHold[monsters.length] = monster;
                    monsters = monHold;
                }
            }
            roomMonsters.add(monster);
        }
        public Characters removeRoomMonster(String name) {
            Characters fin = null;
            for (int i = 0; i < roomMonsters.size(); i++) {
                if (name.equals(roomMonsters.get(i).getName())) {
                    fin = roomMonsters.remove(i);
                }
            }
            return fin;
        }
        

        public Characters getRoomAdventurer(int index) {
            if (index > roomAdventurers.size()) {
                return null;
            }
            else {
                return roomAdventurers.get(index);
            }
        }
        public void addRoomAdventurer(Characters adventurer) {
            boolean found = false;
            if (players == null) {
                players = new Characters[1];
                players[0] = adventurer;
            }
            else {
                for (int i = 0; i < players.length; i++) {
                    if (adventurer.getName().equals(players[i].getName())) {
                        found = true;
                    }
                }
                if (!found) {
                    Characters[] charHold = Arrays.copyOf(players, players.length + 1);
                    charHold[players.length] = adventurer;
                    players = charHold;
                }
            }
            
            roomAdventurers.add(adventurer);
            
        }
        public Characters removeRoomAdventurer(String name) {
            Characters fin = null;
            for (int i = 0; i < roomAdventurers.size(); i++) {
                if (name.equals(roomAdventurers.get(i).getName())) {
                    fin = roomAdventurers.remove(i);
                }
            }
            return fin;
        }

        public Item getRoomItem(int index) {
            return roomItems.get(index);
        }
        public void addRoomItem(Item item) {
            roomItems.add(item);
        }
    }

    private Room[] rooms;
    private int numOfRooms;
    private Characters players[];
    private Characters monsters[];
    private Item items[];

    public Maze() {}

    public Maze(Builder builder) {
        this.rooms = builder.rooms;
        this.numOfRooms = builder.numOfRooms;
        this.players = builder.adventurers;
        this.monsters = builder.monsters;
        this.items = builder.items;
    }

    public static class Builder {
        private Room[] rooms;
        private int numOfRooms;
        private Characters adventurers[] = {};
        private Characters monsters[] = {};
        private Item items[] = {};

        Random rand = new Random(System.currentTimeMillis());

        public static Builder newInstance() {
            return new Builder();
        }

        private Builder() {}

        public Builder addCreatures(Characters[] creatures) {
            Characters holdMonsters[] = Arrays.copyOf(this.monsters, this.monsters.length + creatures.length);
            for (int i = 0; i < creatures.length; i++) {
                holdMonsters[this.monsters.length + i] = creatures[i];
            }
            this.monsters = holdMonsters;
            return this;
        }

        public Builder addAdventurers(Characters[] adventurers) {
            Characters holdAdventurers[] = Arrays.copyOf(this.adventurers, this.adventurers.length + adventurers.length);
            for (int i = 0; i < adventurers.length; i++) {
                holdAdventurers[this.adventurers.length + i] = adventurers[i];
            }
            this.adventurers = holdAdventurers;
            return this;
        }

        public Builder addItems(Item[] items) {
            Item holdItems[] = Arrays.copyOf(this.items, this.items.length + items.length);
            for (int i = 0; i < items.length; i++) {
                holdItems[this.items.length + i] = items[i];
            }
            this.items = holdItems;
            return this;
        }

        public Builder createAndAddAdventurers(String[] names) {
            for (int i = 0; i < names.length; i++) {
                Characters[] adventurer = {AdventurerFactory.createAdventurer("adventurer", names[i])};
                adventurer[0].setIndex(i);
                this.adventurers = addAdventurers(adventurer).adventurers;
            }
            return this;
        }

        public Builder createAndAddCowards(String[] names) {
            for (int i = 0; i < names.length; i++) {
                Characters[] adventurer = {AdventurerFactory.createAdventurer("coward", names[i])};
                adventurer[0].setIndex(i);
                this.adventurers = addAdventurers(adventurer).adventurers;
            }
            return this;
        }

        public Builder createAndAddKnights(String[] names) {
            for (int i = 0; i < names.length; i++) {
                Characters[] adventurer = {AdventurerFactory.createAdventurer("knight", names[i])};
                adventurer[0].setIndex(i);
                this.adventurers = addAdventurers(adventurer).adventurers;
            }
            return this;
        }

        public Builder createAndAddGluttons(String[] names) {
            for (int i = 0; i < names.length; i++) {
                Characters[] adventurer = {AdventurerFactory.createAdventurer("glutton", names[i])};
                adventurer[0].setIndex(i);
                this.adventurers = addAdventurers(adventurer).adventurers;
            }
            return this;
        }

        public Builder createAndAddDemons(String[] names) {
            for (int i = 0; i < names.length; i++) {
                Characters[] creature = {CreatureFactory.createCreature("demon", names[i])};
                creature[0].setIndex(i);
                this.monsters = addCreatures(creature).monsters;
            }
            return this;
        }

        public Builder createAndAddCreatures(String[] names) {
            for (int i = 0; i < names.length; i++) {
                Characters[] creature = {CreatureFactory.createCreature("creature", names[i])};
                creature[0].setIndex(i);
                this.monsters = addCreatures(creature).monsters;
            }
            return this;
        }

        public Builder createAndAddFoodItems(String[] names) {
            for (int i = 0; i < names.length; i++) {
                Item[] items = {ItemFactory.createItem("corn", names[i])};
                this.items = addItems(items).items;
            }
            return this;
        }

        public Builder distributeRandomly() {
            for (int i = 0; i < this.adventurers.length; i++) {
                int randHold = rand.nextInt(this.numOfRooms);
                this.rooms[randHold].addRoomAdventurer(this.adventurers[i]);
                this.adventurers[i].setLocation(randHold + 1);
            }
            for (int i = 0; i < this.monsters.length; i++) {
                int randHold = rand.nextInt(this.numOfRooms);
                this.rooms[randHold].addRoomMonster(this.monsters[i]);
                this.monsters[i].setLocation(randHold + 1);
            }
            for (int i = 0; i < this.items.length; i++) {
                int randHold = rand.nextInt(this.numOfRooms);
                this.rooms[randHold].addRoomItem(this.items[i]);
                this.items[i].setLocation(randHold + 1);
            }
            return this;
        }

        public Builder distribuiteSequentially() { // dosent work
            int count = 0;
            int size = this.adventurers.length + this.monsters.length + this.items.length;
            for (int i = 0; i < size; i++) {
                count = i % this.numOfRooms;
                if (0 <= i && i < this.adventurers.length) {
                    this.rooms[count].addRoomAdventurer(this.adventurers[i]);
                    this.adventurers[i].setLocation(i + 1);
                }
                else if (this.adventurers.length <= i && i < this.adventurers.length + this.monsters.length) {
                    int fakeI = i - this.adventurers.length;
                    this.rooms[count].addRoomMonster(this.monsters[fakeI]);
                    this.monsters[fakeI].setLocation(fakeI + 1);
                }
                else if (this.adventurers.length + this.monsters.length <= i) {
                    int fakeI = i - this.adventurers.length - this.monsters.length;
                    this.rooms[count].addRoomItem(this.items[fakeI]);
                    this.items[fakeI].setLocation(fakeI + 1);
                }
            }
            return this;
        }

        public Builder create3x3Grid() {
            Maze maze = new Maze();
            int[] adj1 = {2, 4};
            int[] adj2 = {1, 5, 3};
            int[] adj3 = {2, 6};
            int[] adj4 = {1, 5, 7};
            int[] adj5 = {2, 4, 6, 8};
            int[] adj6 = {3, 5, 9};
            int[] adj7 = {4, 8};
            int[] adj8 = {5, 7, 9};
            int[] adj9 = {6, 8};

            Room room1 = maze.new Room(1, adj1);
            Room room2 = maze.new Room(2, adj2);
            Room room3 = maze.new Room(3, adj3);
            Room room4 = maze.new Room(4, adj4);
            Room room5 = maze.new Room(5, adj5);
            Room room6 = maze.new Room(6, adj6);
            Room room7 = maze.new Room(7, adj7);
            Room room8 = maze.new Room(8, adj8);
            Room room9 = maze.new Room(9, adj9);

            Room[] RoomHold = {room1, room2, room3, room4, room5, room6, room7, room8, room9};
            this.rooms = RoomHold;
            this.numOfRooms = 9;
            return this;
        }

        // 1 <-> 2
        // |     |
        // 3 <-> 4
        public Builder create2x2Grid() {
            Maze maze = new Maze();
            int[] adj1 = {2, 3};
            int[] adj2 = {1, 4};


            Room room1 = maze.new Room(1, adj1);
            Room room2 = maze.new Room(2, adj2);
            Room room3 = maze.new Room(3, adj2);
            Room room4 = maze.new Room(4, adj1);

            Room[] RoomHold = {room1, room2, room3, room4};
            this.rooms = RoomHold;
            this.numOfRooms = 4;
            return this;
        }

        public Builder createFullyConnectedRooms(int n) {
            Maze maze = new Maze();
            for (int i = 0; i < (n * n); i++) {
                ArrayList<Integer> adjList = new ArrayList<Integer>();
                Maze.Room[] roomHold = new Room[1];
                for (int j = 0; j < (n * n); j++) {
                    if (j != i) {
                        adjList.add(j + 1);
                    }
                }
                int[] adj = new int[adjList.size()];
                for (int j = 0; j < adj.length; j++) {
                    adj[j] = adjList.get(j);
                }

                Room room = maze.new Room(i + 1, adj);
                if (i == 0) {
                    roomHold[0] = room;
                }
                else {
                    roomHold = Arrays.copyOf(this.rooms, this.rooms.length + 1);
                    roomHold[this.rooms.length] = room;
                }
                this.rooms = roomHold;
            }
            this.numOfRooms = n;
            return this;
        }

        public Builder createRoom(int ID) {
            Maze maze = new Maze();
            int[] adj = {};
            Maze.Room room = maze.new Room(ID, adj);
            if (this.rooms != null) {
                Maze.Room[] roomsHold = Arrays.copyOf(this.rooms, this.numOfRooms + 1);
                roomsHold[this.numOfRooms] = room;
                this.numOfRooms += 1;
                this.rooms = roomsHold;
            }
            else {
                this.rooms = new Room[1];
                this.rooms[0] = room;
            }
            
            return this;
        }

        public Builder createConnectedRoom(int ID, int[] adj) {
            Maze maze = new Maze();
            Maze.Room room = maze.new Room(ID, adj);
            if (this.rooms != null) {
                Maze.Room[] roomsHold = Arrays.copyOf(this.rooms, this.numOfRooms + 1);
                roomsHold[this.numOfRooms] = room;
                this.numOfRooms += 1;
                this.rooms = roomsHold;
            }
            else {
                this.rooms = new Room[1];
                this.rooms[0] = room;
            }
            return this;
        }

        public Builder addAdventurerToRoom(int ID, Characters adventurer) {
            this.rooms[ID - 1].addRoomAdventurer(adventurer);
            adventurer.setLocation(ID);
            boolean found = false;
            for (int i = 0; i < adventurers.length; i++) {
                if (adventurer.getName().equals(adventurers[i].getName())) {
                    found = true;
                }
            }
            if (!found) {
                Characters[] adventurersHold = Arrays.copyOf(this.adventurers, this.adventurers.length + 1);
                adventurersHold[this.adventurers.length] = adventurer;
                this.adventurers = adventurersHold;
            }
            return this;
        }
        public Builder addMonsterToRoom(int ID, Characters monster) {
            this.rooms[ID - 1].addRoomMonster(monster);
            monster.setLocation(ID);
            boolean found = false;
            for (int i = 0; i < monsters.length; i++) {
                if (monster.getName().equals(monsters[i].getName())) {
                    found = true;
                }
            }
            if (!found) {
                Characters[] monstersHold = Arrays.copyOf(this.monsters, this.monsters.length + 1);
                monstersHold[this.monsters.length] = monster;
                this.monsters = monstersHold;
            }
            return this;
        }
        public Builder addItemsToRoom(int ID, Item item) {
            this.rooms[ID - 1].addRoomItem(item);
            return this;
        }

        public Maze build() {
            Maze maze = new Maze(this);
            return maze;
        }
    }

    public Characters getPlayerByName(String name) {
        for (int i = 0; i < players.length; i++) {
            if (name.equals(players[i].getName())) {
                return players[i];
            }
        }
        return null;
    }

    public Characters getMonsterByName(String name) {
        for (int i = 0; i < monsters.length; i++) {
            if (name.equals(monsters[i].getName())) {
                return monsters[i];
            }
        }
        return null;
    }

    public int getCreatureLocation(int index) {
        return monsters[index].getLocation();
    }

    public int getPlayerNum() {
        return players.length;
    }

    public int getMonsterNum() {
        return monsters.length;
    }

    public String getAdventurerName(int index) {
        return players[index].getName();
    }

    public String getCreatureName(int index) {
        return monsters[index].getName();
    }

    public Characters getPlayer(int index) {
        return players[index];
    }

    public Characters getMonster(int index) {
        return monsters[index];
    }

    public int getTotalPlayerHealth() {
        int total = 0;
        for (int i = 0; i < players.length; i++) {
            total += players[i].getHealth();
        }
        return total;
    }

    public int getTotalMonsterHealth() {
        int total = 0;
        for (int i = 0; i < monsters.length; i++) {
            total += monsters[i].getHealth();
        }
        return total;
    }

    public int getAdventurerLocation(int index) {
        return players[index].getLocation();
    }


    public int getNumOfRooms() {
        return numOfRooms;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public int getNumberOfItemsInAllRooms() {
        int count = 0;
        Room[] rooms = getRooms();
        for (int i = 0; i < numOfRooms; i++) {
            count += rooms[i].getItemSize();
        }
        return count;
    }

    public void consumeItem(String name, Room room)
    {
        for (Item item : room.roomItems) {
            if (item.getName().equals(name))
            {
                room.roomItems.remove(item);
                break;
            }
        }

    }


    public void movePlayer(int start, int end, Characters player) {
        String name = player.getName();
        for (int i = 0; i < numOfRooms; i++) {
            if (rooms[i].getId() == start) {
                rooms[i].removeRoomAdventurer(name);
            }
            if (rooms[i].getId() == end) {
                rooms[i].addRoomAdventurer(player);
                players[player.getIndex()].setLocation(rooms[i].getId());
            }
        }
    }
}
