import java.util.Random;

public enum GameArtifact {
   SWORD("Sword"),
   /**
    * An enumeration of valid game artifacts.
    */
   REGULARMONSTER("Regular Monster"),
   MAGICSTONES("Magic Stones"),
   STAIRS("Stairs"),
   PRIZE("Prize"),
   BOSSMONSTER("Boss Monster");

   private static final Random rand = new Random();

   private final String displayName;

   GameArtifact(String displayName) {
      this.displayName = displayName;
   }

   /**
    * Gets a randomly chosen game artifact from the list of valid one.
    * @return - a randomly chosen game artifact
    */
   public static GameArtifact getRandomRandomGameArtifact() {
      GameArtifact[] artifacts = GameArtifact.values();
      return artifacts[rand.nextInt(artifacts.length)];
   }

   @Override
   public String toString() {
      return displayName;
   }
}
