import java.util.ArrayList;
import java.util.List;

public class Game {

    private Player player;

    private final int numRooms;
    private final int numFloors;

    private final List<Floor> floors;

    private Floor currentFloor;
    private Room currentRoom;

    public Game(int numFloors, int numRooms) {
        this.numFloors = numFloors;
        this.numRooms = numRooms;

        System.out.println("Game");
        floors = new ArrayList<Floor>();
        for (int i = 0; i < numFloors; i++) {
            System.out.format("Creating floor %d%n", i);
            floors.add(new Floor(i, numRooms));

        }
    }

    public void start() {

        System.out.println("Game started");
        String playerName = InputUtils.getAnswer("Player", "What is your name?");
        player = new Player(playerName);

        // pick a random room on a random floor as the current room.
        int randomFloor = RandomNumberUtilities.getRandomIntInRange(0, numFloors);
        currentFloor = floors.get(randomFloor);

        int randomRoom = RandomNumberUtilities.getRandomIntInRange(0, numRooms);
        currentRoom = currentFloor.getRoom(randomRoom);

        System.out.format("Game is using %d floors with %d rooms per floor%n", numFloors, numRooms);

        displayCurrentRoom();

        boolean keepPlaying = true;
        boolean playAgain = true;
        do {
            // TODO: play until either the Monster gets us or we take away things from the Monster
            do {
                keepPlaying = processNextCommand();
            } while (keepPlaying);

            playAgain = InputUtils.askYesNoQuestion("Continue playing", "Do you want to play again?");
        } while (playAgain);

        System.out.println("Thank you for playing!");

    }

    private boolean processNextCommand() {
        System.out.println("Here is the state of the current room:");
        System.out.println(currentRoom);

        // ask the user what they want to try to do
        Commands nextCommand = InputUtils.getSingleEnumChoice("Next Command", "What do youw want to do?", Commands.class);

        switch (nextCommand) {
            case UP -> {
                // if the current room has stairs and there is a room above us, move up to the same room number on the floor above;
                // otherwise, reject the command
            }

            case DOWN -> {
                // if the current room has stairs and there is a room below us, move down to the same room number on the floor below;
                // otherwise, reject the command
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
            }
        }


        return false;
    }

    public void displayCurrentRoom() {
        System.out.format("Current floor: %s%n", currentFloor);
        System.out.format("Current room: %s%n", currentRoom);
    }
}