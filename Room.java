public class Room {
   private static int NEXT_ROOM_NUMBER = 0;
   
   private int roomNumber;
   public Room() {
      this.roomNumber = NEXT_ROOM_NUMBER++;
      System.out.format("Created room number %d%n", this.roomNumber);
   }
   
   public int getRoomNumber() { return this.roomNumber; }
}
