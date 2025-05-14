import java.util.*;

public class Floor {
   private int floorNumber;
   private final List<Room> rooms;

   public Floor(int floorNumber, int numRooms) {
      this.floorNumber = floorNumber;
      rooms = new ArrayList<Room>(numRooms);
      for(int roomNumber = 0; roomNumber < numRooms; roomNumber++){
         rooms.add(new Room(floorNumber, roomNumber));
         //System.out.format("Created room %d on floor %d%n", roomNumber, floorNumber);
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
      sb.append("\nrooms:\n");
      for(Room room : rooms){
         sb.append("\t");
         sb.append(room);
         sb.append("\n");
      }
      sb.append("}");
      return sb.toString();
   }
}