import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The game prints out the contents of the current room after every command.
 * The user can grab swords or magic stones if they walk into a room containing
 * either of these items.
 * The sword or stones are no longer in the room once grabbed.
 */
public class Room {

   private final int floorNumber;
   private  int roomNumber;

   @Override
   public boolean equals(Object o) {
      // since the number and type of artifacts in a given room may differ,
      // we only care about floor number and room number when check for equality
      // amoung instances.
      if (this == o) return true;
      if (!(o instanceof Room room)) return false;
      return floorNumber == room.floorNumber
          && roomNumber == room.roomNumber;
   }

   @Override
   public int hashCode() {
      return Objects.hash(floorNumber, roomNumber);
   }

   private List<GameArtifact> artifacts;
   private boolean hasVisited = false;

   public Room(int floorNumber, int roomNumber) {
      this.floorNumber = floorNumber;
      this.roomNumber = roomNumber;
      this.artifacts = new ArrayList<GameArtifact>();
   }

   
   public int getRoomNumber() { return this.roomNumber; }
   public void setRoomNumber(int roomNumber) {this.roomNumber = roomNumber;}

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("Room{");
      sb.append("roomNumber=").append(roomNumber);
      sb.append(", floorNumber=").append(floorNumber);
      sb.append(", hasVisited=").append(hasVisited);
      sb.append(", artifacts=").append(artifacts);
      sb.append('}');
      return sb.toString();
   }

    public boolean isHasVisited() {return hasVisited;}
    public void setHasVisited(boolean hasVisited) { this.hasVisited = hasVisited;}

   public boolean hasStairs() {
      return artifacts.contains(GameArtifact.STAIRS);
   }

   public int getFloorNumber() {return floorNumber;  }

   public boolean canGrab() {
      return artifacts.contains(GameArtifact.SWORD) || artifacts.contains(GameArtifact.MAGICSTONES);
   }

   public boolean hasSword() {
      return artifacts.contains(GameArtifact.SWORD);
   }

   public void removeSword() {
      artifacts.remove(GameArtifact.SWORD);
   }

   public boolean hasMagicStones() {
      return artifacts.contains(GameArtifact.MAGICSTONES);
   }

   public void removeMagicStones() {
      artifacts.remove(GameArtifact.MAGICSTONES);
   }

   public boolean hasRegularMonster() {return artifacts.contains(GameArtifact.REGULARMONSTER);}
   public boolean hasBossMonster() {return artifacts.contains(GameArtifact.BOSSMONSTER);}

   public void removeArtifact(GameArtifact gameArtifact) {
      artifacts.remove(gameArtifact);
   }

   public List<GameArtifact> getArtifacts() {return artifacts;}

   public void addArtifact(GameArtifact ga) {
      artifacts.add(ga);
   }
}
