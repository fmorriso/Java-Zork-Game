import java.util.ArrayList;
import java.util.List;

public class Floor {
   private int floorNumber;
   private List<Room> rooms;

   public boolean hasAtLeastOneRoomWithStairs() {
      return hasAtLeastOneRoomWithStairs;
   }

   public void setHasAtLeastOneRoomWithStairs(boolean hasAtLeastOneRoomWithStairs) {
      this.hasAtLeastOneRoomWithStairs = hasAtLeastOneRoomWithStairs;
   }

   private boolean hasAtLeastOneRoomWithStairs;

   public Floor(int floorNumber, int numRooms) {
      this.floorNumber = floorNumber;
      rooms = new ArrayList<Room>(numRooms);
   }

   public int getFloorNumber() {return floorNumber;}
   public List<Room> getRooms() {return rooms; }
   public void addRoom(Room room) {rooms.add(room); }


   public Room getRoom(int n) {
      return rooms.get(n);
   }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("Floor{");
      sb.append("floorNumber=").append(floorNumber + 1);
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