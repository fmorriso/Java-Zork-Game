import java.lang.*;
import java.util.*;

public class Game {


   private List<Floor> floors;
   public Game(int numFloors, int numRooms) {
      System.out.println("Game");
      floors = new ArrayList<Floor>();
      for(int i = 0; i < numFloors; i++) {
         floors.add(new Floor(numRooms) );
      }
   }
   
   public void start() {
      System.out.println("Game started");
   }
}