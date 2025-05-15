import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final int MAX_REGULAR_MONSTERS = 3;

    private Player player;

    private int numRooms;
    private int numFloors;

    private List<Floor> floors;

    private Floor currentFloor;
    private Room currentRoom;

    private int numRegularMonsters = 0;
    private boolean haveBossMonster = false;
    private boolean havePrize = false;
    private boolean haveSword;
    private boolean haveMagicStones;

    public Game(int numFloors, int numRooms) {
        this.numFloors = numFloors;
        this.numRooms = numRooms;

        System.out.println("Game");

    }

    public void start() {

        System.out.println("Game started");
        String playerName = InputUtils.getAnswer("Player", "What is your name?");
        player = new Player(playerName);
        System.out.format("Player: %s%n", player);

        boolean playAgain = true;
        do {
            resetGame();
            boolean keepPlaying = true;
            // play until either the Monster gets us or we take away things from the Monster
            do {
                keepPlaying = processNextCommand();
            } while (keepPlaying);

            playAgain = InputUtils.askYesNoQuestion("Continue playing", "Do you want to play again?");
        } while (playAgain);

        System.out.println("Thank you for playing!");

    }

    private void resetGame() {
        floors = new ArrayList<Floor>();
        player.clearArtifacts();
        player.setAlive(true);

        haveBossMonster = false;
        numRegularMonsters = 0;
        haveSword = false;
        havePrize = false;
        haveMagicStones = false;

        for (int i = 0; i < numFloors; i++) {
            System.out.format("Creating floor %d%n", i);
            Floor f = new Floor(i, numRooms);
            populateFloor(f);
            floors.add(f);
        }

        // if we have no Boss Monster yet, select a random room in a random floor to put the Boss Monster and the Prize
        if(!haveBossMonster) {
            int floorNum = RandomNumberUtilities.getRandomIntInRange(0, numFloors, false);
            Floor floor = floors.get(floorNum);

            int roomNum = RandomNumberUtilities.getRandomIntInRange(0, numRooms, false);
            Room room = floor.getRoom(roomNum);

            room.addArtifact(GameArtifact.BOSSMONSTER);
            haveBossMonster = true;

            // prize must be in the same room as the BOSSMONSTER
            if(!havePrize) {
                room.addArtifact(GameArtifact.PRIZE);
                havePrize = true;
            }

        }

        // if no room has a sword, pick a random room to put one in.
        if(!haveSword) {
            int floorNum = RandomNumberUtilities.getRandomIntInRange(0, numFloors, false);
            Floor floor = floors.get(floorNum);

            int roomNum = RandomNumberUtilities.getRandomIntInRange(0, numRooms, false);
            Room room = floor.getRoom(roomNum);

            room.addArtifact(GameArtifact.SWORD);
            haveSword = true;
        }

        // if no room has magic stones, pick a random room to put them in
        if(!haveMagicStones) {
            int floorNum = RandomNumberUtilities.getRandomIntInRange(0, numFloors, false);
            Floor floor = floors.get(floorNum);

            int roomNum = RandomNumberUtilities.getRandomIntInRange(0, numRooms, false);
            Room room = floor.getRoom(roomNum);

            room.addArtifact(GameArtifact.MAGICSTONES);
            haveMagicStones = true;
        }

        // pick a random room on a random floor as the current room.
        int randomFloor = RandomNumberUtilities.getRandomIntInRange(0, numFloors - 1);
        currentFloor = floors.get(randomFloor);

        int randomRoom = RandomNumberUtilities.getRandomIntInRange(0, numRooms - 1);
        currentRoom = currentFloor.getRoom(randomRoom);
        currentRoom.setHasVisited(true);

        System.out.format("Game is using %d floors with %d rooms per floor%n", numFloors, numRooms);

        displayCurrentGameStatus();
    }

    private void populateFloor(Floor f) {
        int floorNumber = f.getFloorNumber();
        for(int roomNumber = 0; roomNumber < numRooms; roomNumber++){
            System.out.format("\tPopulating room %d%n", roomNumber);
            Room r = new Room(floorNumber, roomNumber);
            populateRoomWithRandomArtifacts(r);
            f.addRoom(r);
        }

        // Make sure at least one room on each floor has stairs
        if(!f.hasAtLeastOneRoomWithStairs()){
            int roomNum = RandomNumberUtilities.getRandomIntInRange(0, numRooms, false);
            Room r = f.getRoom(roomNum);
            r.addArtifact(GameArtifact.STAIRS);
        }
    }

    private void populateRoomWithRandomArtifacts(Room r) {
        // note: we allow a list length of zero to indicate there is Nothing in the room.
        int numArtifacts = RandomNumberUtilities.getRandomIntInRange(0, 1, true);

        final int MAX_LOOP_ATTEMPTS = GameArtifact.values().length;

        for (int i = 0, attempts = 0; i < numArtifacts && attempts < MAX_LOOP_ATTEMPTS; attempts++) {
            //System.out.format("\t\tattempts=%d%n", attempts);
            GameArtifact ga = GameArtifact.getRandomRandomGameArtifact();

            // ignore Boss Monster and Prize until later
            if(ga.equals(GameArtifact.BOSSMONSTER)) continue;
            if(ga.equals(GameArtifact.PRIZE)) continue;

            // only one Sword per game
            if(haveSword) continue;

            // only one pile of Magic Stones per game
            if(haveMagicStones) continue;

            // cannot exceed maximum allowable regular monsters per game, regardless of floor or room
            if(numRegularMonsters == MAX_REGULAR_MONSTERS) continue;

            // avoid duplicate artifacts in the same room
            if (!r.getArtifacts().contains(ga)) {

                r.addArtifact(ga);

                switch (ga) {

                    case REGULARMONSTER:
                        numRegularMonsters++;
                        break;

                    case SWORD:
                        haveSword = true;
                        break;

                    case MAGICSTONES:
                        haveMagicStones = true;
                        break;

                }

                i++;
            }
        }
    }

    private boolean processNextCommand() {
        System.out.println("Before next command:");
        displayCurrentGameStatus();

        // ask the user what they want to try to do
        Commands nextCommand = InputUtils.getSingleEnumChoice("Next Command", "What do you want to do?", Commands.class);
        System.out.println("Processing command: " + nextCommand);
        switch (nextCommand) {

            case UP:
                // if the current room has stairs and there is a room above us, move up to the same room number on the floor above;
                // otherwise, reject the command
                if (currentRoom.hasStairs()) {
                    System.out.format("DEBUG: trying to go up from current floor number %d%n", currentRoom.getFloor());
                    if (currentRoom.getFloor() > 0) {
                        player.setPreviousRoom(currentRoom);
                        currentFloor = floors.get(currentRoom.getFloor() - 1);
                        currentRoom = currentFloor.getRoom(currentRoom.getRoomNumber());
                        currentRoom.setHasVisited(true);
                    } else {
                        System.err.println("You're already on the top floor.  Command rejected.");
                    }
                } else {
                    System.err.println("The current room does not have stairs.  Command rejected.");
                }
                break;

            case DOWN:
                // if the current room has stairs and there is a room below us, move down to the same room number on the floor below;
                // otherwise, reject the command
                if (currentRoom.hasStairs()) {
                    System.out.format("DEBUG: trying to go down from current floor number %d%n", currentRoom.getFloor());
                    if (currentRoom.getFloor() < numFloors - 1) {
                        player.setPreviousRoom(currentRoom);
                        currentFloor = floors.get(currentRoom.getFloor() + 1);
                        currentRoom = currentFloor.getRoom(currentRoom.getRoomNumber());
                        currentRoom.setHasVisited(true);
                    } else {
                        System.err.println("You're already on the bottom floor.  Command rejected.");
                    }
                } else {
                    System.err.println("The current room does not have stairs.  Command rejected.");
                }
                break;

            case LEFT:// if there is a room to our left, move to that room depending on monster presence;
                // otherwise, reject the command
                if (currentRoom.getRoomNumber() > 0) {
                    Room destinationRoom = currentFloor.getRoom(currentRoom.getRoomNumber() - 1);
                    // check for monster in current room
                    if (currentRoom.hasRegularMonster()) {
                        // if user is backing out to the same room they were in previously, allow it, even if the user
                        // has the ability to fight the monster
                        if (player.getPreviousRoom() == null || destinationRoom.equals(player.getPreviousRoom())) {
                            player.setPreviousRoom(currentRoom);
                            currentRoom = destinationRoom;
                            currentRoom.setHasVisited(true);
                        } else if (player.canFightRegularMonster()) {
                            // trying to walk past a monster in a direction away from the previous room, so player gets killed by the monster.
                            System.err.println("You're trying to walk past a monster you could fight.  You are killed by that monster.");
                            player.setAlive(false);
                            return false; // end game
                        } else {
                            System.err.println("When a monster is present but you are unable to fight it, you can only return to the previous room. Command rejected.");
                        }
                    } else {
                        // no monster and there is a room on the left, so allow it.
                        player.setPreviousRoom(currentRoom);
                        currentRoom = destinationRoom;
                        currentRoom.setHasVisited(true);
                    }
                } else {
                    System.err.println("You're already at the left-most room of the floor.  Command rejected.");
                }
                break;

            case RIGHT:// if there is a room to our right, move to that room;
                // otherwise, reject the command
                if (currentRoom.getRoomNumber() < numRooms - 1) {
                    Room destinationRoom = currentFloor.getRoom(currentRoom.getRoomNumber() + 1);
                    // If they try to walk past a monster they could fight, they will be killed and the game will end.
                    if (currentRoom.hasRegularMonster()) {
                        if (player.getPreviousRoom() == null || destinationRoom.equals(player.getPreviousRoom())) {
                            player.setPreviousRoom(currentRoom);
                            currentRoom = destinationRoom;
                            currentRoom.setHasVisited(true);
                        } else if (player.canFightRegularMonster()) {
                            System.err.println("You're trying to walk past a monster you could fight.  You are killed by that monster.");
                            player.setAlive(false);
                            return false; // end game
                        } else {
                            System.err.println("When a monster is present but you are unable to fight it, you can only return to the previous room. Command rejected.");
                        }

                    } else {
                        player.setPreviousRoom(currentRoom);
                        currentRoom = destinationRoom;
                        currentRoom.setHasVisited(true);
                    }
                } else {
                    System.err.println("You're already at the right-most room of the floor.  Command rejected.");
                }
                break;

            case GRAB:// if the room contains swords or magic stones, grab them and remove them from the room
                if (currentRoom.canGrab()) {
                    if (currentRoom.hasSword()) {
                        currentRoom.removeSword();
                        player.addArtifact(GameArtifact.SWORD);
                    }
                    if (currentRoom.hasMagicStones()) {
                        currentRoom.removeMagicStones();
                        player.addArtifact(GameArtifact.MAGICSTONES);
                    }
                    System.out.println(player);
                } else {
                    System.err.println("The current room has nothing for you to grab.  Command rejected.");
                }
                break;

            case FIGHT:// if the room contains a monster and the player has the necessary weapons, the user can use the
                // sword to defeat the monster, which removes the monster from the room and removes the
                // sword from the player.
                // If the room contains a monster and the player tries to fight the monster but lacks
                // the necessary weapons, they are killed and the game ends.
                // Otherwise, the command is rejected (because there is no monster to fight).
                if (currentRoom.hasRegularMonster()) {
                    if (player.canFightRegularMonster()) {
                        currentRoom.removeArtifact(GameArtifact.REGULARMONSTER);
                        player.removeArtifact(GameArtifact.SWORD);
                        System.out.println("You killed a regular monster.");
                    } else {
                        // If the user fights without a sword, they will be defeated and the game will end.
                        System.err.println("Player chose to fight a regular monster without a sword.  Regular Monster kills player!");
                        player.setAlive(false);
                        return false;
                    }
                } else if (currentRoom.hasBossMonster()) {
                    if (player.canFightBossMonster()) {
                        currentRoom.removeArtifact(GameArtifact.REGULARMONSTER);
                        player.removeArtifact(GameArtifact.SWORD);
                        player.addArtifact(GameArtifact.MAGICSTONES);
                        System.out.println("You killed a regular monster.");
                    } else {
                        // If the user fights without a sword, they will be defeated and the game will end.
                        System.err.println("Player chose to fight the boss monster without the necessary weapons.  Boss monster kills player!");
                        player.setAlive(false);
                        return false;
                    }
                } else {
                    System.err.println("The current room has no monster.  Command rejected.");
                }
                break;

            case HELP:// display game help
                break;

            default:
                System.err.println("Empty or unrecognized command encountered.  No action taken.");
                break;
        }

        System.out.println("After command processing, the current room:");
        displayCurrentGameStatus();


        return true;
    }

    public void displayCurrentGameStatus() {
        StringBuilder sb = new StringBuilder();
        for(Floor floor : floors){
            sb.append("\nFloor number: ");
            sb.append(floor.getFloorNumber());
            sb.append("\n");
            for(Room room : floor.getRooms()){
                sb.append("\t");
                sb.append(room.toString());
                sb.append("\n");
            }

        }
        sb.append("Current ");
        sb.append(currentRoom.toString());
        System.out.println(sb.toString());

        System.out.format("Player: %s%n", player);
    }
}