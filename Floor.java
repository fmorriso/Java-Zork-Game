import java.util.*;

public class Floor {
   private List<Room> rooms;
   public Floor(int numRooms) {
      rooms = new ArrayList<Room>(numRooms);
      for(int i = 0; i < numRooms; i++){
         Room r = new Room();
         rooms.add(r);
      }
   }
}