import java.util.Random;

public enum GameArtifact {
   SWORD,
   REGULARMONSTER,
   MAGICSTONES,
   STAIRS,
   PRIZE,
   BOSSMONSTER;

   private static final Random rand = new Random();

   public static GameArtifact getRandomRandomGameArtifact() {
      GameArtifact[] artifacts = GameArtifact.values();
      return artifacts[rand.nextInt(artifacts.length)];
   }
}
