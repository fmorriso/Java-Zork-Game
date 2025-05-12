import java.util.ArrayList;
import java.util.List;

public class Room {

   
   private final int roomNumber;
   private List<GameArtifact> artifacts;

   public Room(int roomNumber) {
      this.roomNumber = roomNumber;
      populateRoomWithRandomArtifacts();
   }

   private void populateRoomWithRandomArtifacts() {
      // note: we allow a list length of zero to indicate there is Nothing in thr room.
      int numArtifacts = RandomNumberUtilities.getRandomIntInRange(0, GameArtifact.values().length);
      artifacts = new ArrayList<GameArtifact>();
      for (int i = 0; i < numArtifacts; ) {
         GameArtifact ga = GameArtifact.getRandomRandomGameArtifact();
         // avoid duplicate artifacts in the same room
         if (!artifacts.contains(ga)) {
            artifacts.add(ga);
            i++;
         }
      }
      int x = 0; // debug stop
   }
   
   public int getRoomNumber() { return this.roomNumber; }

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("Room{");
      sb.append("roomNumber=").append(roomNumber);
      sb.append(", artifacts=").append(artifacts);
      sb.append('}');
      return sb.toString();
   }
}
