import java.util.*;

public class Floor {
   private int floorNumber;
   private final List<Room> rooms;

   public Floor(int floorNumber, int numRooms) {
      this.floorNumber = floorNumber;
      rooms = new ArrayList<Room>(numRooms);
      for(int i = 0; i < numRooms; i++){
         rooms.add(new Room(i));
         System.out.format("Created room %d%n", i);
      }
      generateRandomArtifacts();
   }

   private void generateRandomArtifacts() {
      int numArtifacts = RandomNumberUtilities.getRandomIntInRange(0, GameArtifact.values().length);
   }


   public Room getRoom(int n) {
      return rooms.get(n);
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("Floor{");
      sb.append("floorNumber=").append(floorNumber);
      sb.append(", rooms=").append(rooms);
      sb.append('}');
      return sb.toString();
   }
}