import java.util.Random;

/**
 * An enumeration of valid game artifacts.
 */
public enum GameArtifact {
   SWORD("Sword"),
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
    * Gets a randomly chosen game artifact from the list of valid ones.
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
