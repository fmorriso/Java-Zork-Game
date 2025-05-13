import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player player;

    private int numRooms;
    private int numFloors;

    private List<Floor> floors;

    private Floor currentFloor;
    private Room currentRoom;

    public Game(int numFloors, int numRooms) {
        this.numFloors = numFloors;
        this.numRooms = numRooms;

        System.out.println("Game");

    }

    public void start() {

        System.out.println("Game started");
        String playerName = InputUtils.getAnswer("Player", "What is your name?");
        player = new Player(playerName);

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

        for (int i = 0; i < numFloors; i++) {
            System.out.format("Creating floor %d%n", i);
            floors.add(new Floor(i, numRooms));
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

    private boolean processNextCommand() {
        System.out.println("Before next command, the current room:");
        displayCurrentGameStatus();

        // ask the user what they want to try to do
        Commands nextCommand = InputUtils.getSingleEnumChoice("Next Command", "What do youw want to do?", Commands.class);

        switch (nextCommand) {
            case UP -> {
                // if the current room has stairs and there is a room above us, move up to the same room number on the floor above;
                // otherwise, reject the command
                if(currentRoom.hasStairs()) {
                    if(currentRoom.getFloor() > 0) {
                        currentFloor = floors.get(currentRoom.getFloor() - 1);
                        currentRoom = currentFloor.getRoom(currentRoom.getRoomNumber());
                        currentRoom.setHasVisited(true);
                    } else {
                        System.err.println("You're already on the top floor.  Command rejected.");
                    }
                } else {
                    System.err.println("The current room does not have stairs.  Command rejected.");
                }
            }

            case DOWN -> {
                // if the current room has stairs and there is a room below us, move down to the same room number on the floor below;
                // otherwise, reject the command
                if(currentRoom.hasStairs()) {
                    if(currentRoom.getFloor() < numRooms - 1) {
                        currentFloor = floors.get(currentRoom.getFloor() + 1);
                        currentRoom = currentFloor.getRoom(currentRoom.getRoomNumber());
                        currentRoom.setHasVisited(true);
                    } else {
                        System.err.println("You're already on the bottom floor.  Command rejected.");
                    }
                } else {
                    System.err.println("The current room does not have stairs.  Command rejected.");
                }

            }
            case LEFT -> {
                // if there is a room to our left, move to that room;
                // otherwise, reject the command
            }
            case RIGHT -> {
                // if there is a room to our right, move to that room;
                // otherwise, reject the command
            }
            case GRAB -> {
                // if the room contains swords or magic stones, grab them and remove them from the room
                if (currentRoom.canGrab()) {
                    if(currentRoom.hasSword()) {
                        currentRoom.removeSword();
                        player.addArtifact(GameArtifact.SWORD);
                    }
                    if(currentRoom.hasMagicStones()) {
                        currentRoom.removeMagicStones();
                        player.addArtifact(GameArtifact.MAGICSTONES);
                    }
                    System.out.println(player);
                } else {
                    System.err.println("The current floor has nothing for you to grab.  Command rejected.");
                }
            }
            case FIGHT -> {
                // if the room contains a monster and the player possesses a sword, the user can use the
                // sword to defeat the monster, which removes the monster from the room and removes the
                // sword from the player;
                // otherwise, reject the command
                if(currentRoom.hasMonster()){
                    if(player.canFight() ) {
                        currentRoom.removeArtifact(GameArtifact.MONSTER);
                        player.removeArtifact(GameArtifact.SWORD);
                    } else {
                        // If the user fights without a sword, they will be defeated and the game will end.
                        System.err.println("Player cannot fight the monster without a weapon.  Monster wins!");
                        player.setAlive(false);
                        return false;
                    }

                } else {
                    System.err.println("The current room has no monsters.  Command rejected.");
                }

            }
            case  HELP -> {
                // display game help
            }
            case null -> {
                System.err.println("Null command encountered");
            }

        }

        System.out.println("After command processing, the current room:");
        displayCurrentGameStatus();


        return true;
    }

    public void displayCurrentGameStatus() {
        System.out.format("Current floor: %s%n", currentFloor);
        System.out.format("Current room: %s%n", currentRoom);
        System.out.format("Player: %s%n", player);
    }
}